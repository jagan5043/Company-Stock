package com.cts.company.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.cts.company.entities.Company;
import com.cts.company.repository.CompanyDAO;

@Component
public class CompanyServiceImpl implements CompanyService {
	@Autowired
	CompanyDAO companyDAO;

	@Override
	public void createCompany(Company company) {
		companyDAO.createCompany(company);

	}

	@Override
	public List<Company> getCompany(String companyCode) {
		List<Company> company = companyDAO.getCompany(companyCode);
		return company;
	}

	@Override
	public List<Company> getAllCompany() {
		List<Company> companyList = companyDAO.getAllCompany();
		return companyList;
	}

	@Override
	public Company deleteCompany(String companycode) {
		Company companyDeleted = companyDAO.deleteCompany(companycode);
		return companyDeleted;
	}

	@Override
	public Company getCompany(int companyCode) {
		Company company = companyDAO.getCompany(companyCode);
		return company;
	}

}
