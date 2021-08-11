package com.cts.company.controller;

import java.sql.Date;
import java.sql.Timestamp;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

import org.springframework.amqp.rabbit.core.RabbitTemplate;
//import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import com.cts.company.config.MessagingConfig;
import com.cts.company.entities.Company;
import com.cts.company.entities.Stock;
import com.cts.company.repository.DAOService;
import com.cts.company.service.CompanyService;
import com.cts.company.service.EmailService;
import com.cts.company.service.SMSService;

import javax.mail.MessagingException;
import javax.mail.internet.AddressException;

@RestController
@RequestMapping("/api/v1.0/market/company/")
@CrossOrigin(origins="*")
public class CompanyController {
	@Autowired
	CompanyService companyService;

	@Autowired
	EmailService emailService;
	@Autowired
	DAOService daoService;
	@Autowired
	SMSService smsService;
	@Autowired
	private RabbitTemplate template;

	private static final String POST_URL = "http://stock-env.eba-uaiy3qpa.us-east-2.elasticbeanstalk.com/api/v1.0/market/stock/add/{companyCode}";
	private static final String DELETE_URL = "http://stock-env.eba-uaiy3qpa.us-east-2.elasticbeanstalk.com/api/v1.0/market/stock/delete/{companyCode}";

	@RequestMapping(value = "/register", method = RequestMethod.POST)
	public String createCompany(@RequestBody Company company) {

		String companyCode = company.getCompanyCode();

		java.sql.Date startDate = new java.sql.Date(new java.util.Date().getTime());

		Calendar c = Calendar.getInstance();
		c.setTime(startDate);
		c.add(Calendar.DATE, 100);

		Date endDate = new Date(c.getTimeInMillis());

		Stock stock = new Stock();
		stock.setStockPrice(company.getStockPrice());
		stock.setStartDate(startDate);
		stock.setEndDate(endDate);

		Map<String, String> uriVariable = new HashMap<>();
		uriVariable.put("companyCode", companyCode);

		new RestTemplate().postForEntity(POST_URL, stock, Stock.class, uriVariable);

		companyService.createCompany(company);
		return "Company added successfully!!";
	}

	@RequestMapping(value = "/info/{companyCode}", method = RequestMethod.GET)
	public List<Company> getCompany(@PathVariable String companyCode) {
		List<Company> company = companyService.getCompany(companyCode);
		template.convertAndSend(MessagingConfig.EXCHANGE, MessagingConfig.ROUTING_KEY, company);
		return company;
	}

	@RequestMapping(value = "/getall", method = RequestMethod.GET)
	public List<Company> getAllCompany() {
		List<Company> companyList = companyService.getAllCompany();
		template.convertAndSend(MessagingConfig.EXCHANGE, MessagingConfig.ROUTING_KEY, companyList);
		return companyList;
	}

	@RequestMapping(value = "/delete/{companyCode}", method = RequestMethod.DELETE)
	public Company deleteCompany(@PathVariable String companyCode) {
		Map<String, String> uriVariable = new HashMap<>();
		uriVariable.put("companyCode", companyCode);

		new RestTemplate().delete(DELETE_URL, uriVariable);
		Company companyDeleted = companyService.deleteCompany(companyCode);
		return companyDeleted;
	}

	@RequestMapping(value = "/info/{companyCode}/{2facode}", method = RequestMethod.GET)
	public Object verify(@PathVariable("companyCode") String companyCode, @PathVariable("2facode") String code) {

		boolean isValid = daoService.checkCode(1, code);

		if (isValid) {
			List<Company> company = companyService.getCompany(companyCode);
			return (Company) company;
		}

		return new ResponseEntity<>(HttpStatus.FORBIDDEN);
	}

	@RequestMapping(value = "/users/{userid}/emails/{emailid}/2fa", method = RequestMethod.PUT)
	public ResponseEntity<Object> send2faCodeinEmail(@PathVariable("userid") String id,
			@PathVariable("emailid") String emailid) throws AddressException, MessagingException {
		String twoFaCode = String.valueOf(new Random().nextInt(9999) + 1000);
		emailService.sendEmail(emailid, twoFaCode);
		daoService.update2FAProperties(id, twoFaCode);
		return new ResponseEntity<>(HttpStatus.OK);
	}

	@RequestMapping(value = "/users/{userid}/mobilenumbers/{mobilenumber}/2fa", method = RequestMethod.PUT)
	public ResponseEntity<Object> send2faCodeinSMS(@PathVariable("userid") String id,
			@PathVariable("mobilenumber") String mobile) {
		String twoFaCode = String.valueOf(new Random().nextInt(9999) + 1000);
		smsService.send2FaCode(mobile, twoFaCode);
		daoService.update2FAProperties(id, twoFaCode);
		return new ResponseEntity<>(HttpStatus.OK);
	}

	@RequestMapping(value = "/users/{userid}/codes/{2facode}/info/{companyCode}", method = RequestMethod.PUT)
	public Object verify1(@PathVariable("userid") int id, @PathVariable("2facode") String code, @PathVariable() String companyCode) {

		boolean isValid = daoService.checkCode1(id, code);

		if (isValid) {
			List<Company> company = companyService.getCompany(companyCode);
			return (List<Company>) company;
		}

		return new ResponseEntity<>(HttpStatus.FORBIDDEN);
	}

}
