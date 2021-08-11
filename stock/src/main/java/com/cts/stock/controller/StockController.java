package com.cts.stock.controller;

import java.sql.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.cts.stock.entities.Stock;
import com.cts.stock.entities.StockPriceEntity;
import com.cts.stock.service.StockService;

@RestController
@RequestMapping(value = "/api/v1.0/market/stock")
public class StockController {

	@Autowired
	private StockService stockService;

	@RequestMapping(value = "/add/{companyCode}", method = RequestMethod.POST)
	public Stock addStock(@PathVariable String companyCode, @RequestBody Stock stock) {

		Stock stockObject = new Stock();
		stockObject.setCompanyCode(companyCode);
		stockObject.setStockPrice(stock.getStockPrice());
		stockObject.setStartDate(stock.getStartDate());
		stockObject.setEndDate(stock.getEndDate());
		Stock st = stockService.addStock(stockObject);
		return st;
	}

	@RequestMapping(value = "/get/{companyCode}/{startDate}/{endDate}", method = RequestMethod.GET)
	public StockPriceEntity getStock(@PathVariable String companyCode, @PathVariable("startDate") Date startDate,
			@PathVariable("endDate") Date endDate) {
		StockPriceEntity st = stockService.getStock(companyCode, startDate, endDate);
		return st;
	}

	@RequestMapping(value = "/delete/{companyCode}", method = RequestMethod.DELETE)
	public Void deleteStock(@PathVariable String companyCode) {
		stockService.deleteStock(companyCode);
		return null;
	}
}
