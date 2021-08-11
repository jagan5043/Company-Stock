package com.cts.company.repository;


import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class DAOService {

	@Autowired
	JdbcTemplate jdbcTemplate;
	
	public void update2FAProperties(String userid, String twofacode) {
		jdbcTemplate.update("update stock_market.users set 2fa_code=?, 2fa_expire_time=? where id=?", new Object[] {
				twofacode, (System.currentTimeMillis()/1000) + 120, userid
		});
	}

	@SuppressWarnings("deprecation")
	public boolean checkCode(int id, String code) {
		return jdbcTemplate.queryForObject("select count(*) from users where 2fa_code=? and id=?"
				, new Object[] {code, id}, Integer.class) >0; 
				
	}
	
	public boolean checkCode1(int id, String code) {
		return jdbcTemplate.queryForObject("select count(*) from users where 2fa_code=? and id=?"
				+ " and 2fa_expire_time >=?", new Object[] {code, id, 
						System.currentTimeMillis()/1000}, Integer.class) >0; 
	}
	
}