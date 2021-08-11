package com.cts.company.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.cts.company.entities.Company;

@Service
public interface CompanyService {
	public void createCompany(Company company);

	public List<Company> getCompany(String companyCode);
	
	public Company getCompany(int companyCode);

	public List<Company> getAllCompany();

	public Company deleteCompany(String companycode);

}
