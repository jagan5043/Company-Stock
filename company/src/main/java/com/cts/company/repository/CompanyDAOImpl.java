package com.cts.company.repository;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.cts.company.entities.Company;

@Repository
public class CompanyDAOImpl implements CompanyDAO {

	@Autowired
	private SessionFactory sessionFactory;
	
	@Override
	public void createCompany(Company company) {
		Session session = null;
		try {
			session = sessionFactory.openSession();
			session.beginTransaction();
			session.save(company);
			session.getTransaction().commit();
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	@Override
	public List<Company> getCompany(String companyCode) {
		Session session = null;
		try {
			session = sessionFactory.openSession();
			session.beginTransaction();
			//List<Company> company =  sessionFactory.openSession().createQuery("from Company").list();
			Query query = session.createQuery("from Company where companyCode = :companyCode");
			query.setParameter("companyCode", companyCode);
			List result = query.list();
			session.getTransaction().commit();
			return result;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	
	@Override
	public Company getCompany(int companyCode) {
		Session session = null;
		try {
			session = sessionFactory.openSession();
			session.beginTransaction();
			Company company = session.get(Company.class, companyCode);
			session.getTransaction().commit();
			return company;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public List<Company> getAllCompany() {
		Session session = null;
		try {
			session = sessionFactory.openSession();
			session.beginTransaction();
			List<Company> company =  sessionFactory.openSession().createQuery("from Company").list();
			session.getTransaction().commit();
			return company;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	
	@Override
	public Company deleteCompany(String companyCode) {
		Session session = null;
		try {
			session = sessionFactory.openSession();
			session.beginTransaction();
			/*Company company = session.get(Company.class, companyCode);
			session.delete(company);*/
			Query query = session.createQuery("delete Company where companyCode = :companyCode");
			query.setParameter("companyCode", companyCode);
			 
			int result = query.executeUpdate();
			session.getTransaction().commit();
			//return company;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

}
