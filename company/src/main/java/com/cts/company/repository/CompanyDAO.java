package com.cts.company.repository;

import java.util.List;

import org.springframework.stereotype.Component;

import com.cts.company.entities.Company;

@Component
public interface CompanyDAO {
	public void createCompany(Company company);

	public List<Company> getCompany(String companyCode);
	
	public Company getCompany(int companyCode);
	
	public List<Company> getAllCompany();

	public Company deleteCompany(String companycode);
}
