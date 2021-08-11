package com.cts.stock.repository;

import java.sql.Date;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.NoSuchElementException;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.cts.stock.entities.Stock;
import com.cts.stock.entities.StockPriceEntity;

@Component
public class StockDAOImpl implements StockDAO {

	@Autowired
	private SessionFactory sessionFactory;
	
	@Autowired
	private StockPriceEntity stockPriceEntity;

	@Override
	public Stock addStock(Stock stock) {
		Session session = null;
		try {
			session = sessionFactory.openSession();
			session.beginTransaction();
			session.save(stock);
			session.getTransaction().commit();
			// return st;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;

	}

	@Override
	public StockPriceEntity getStock(String companyCode, Date startDate, Date endDate) {
		Session session = null;
		try {
			session = sessionFactory.openSession();
			session.beginTransaction();

			Query query = session.createQuery("select s.stockPrice from Stock s where s.startDate=:startDate and s.endDate=:endDate and s.companyCode=:companyCode");
					//"FROM Stock s where s.startDate=:startDate and s.endDate=:endDate and s.companyCode=:companyCode");

			query.setParameter("startDate", startDate);
			query.setParameter("endDate", endDate);
			query.setParameter("companyCode", companyCode);
			
			
			
			List l = query.list();
			System.out.println("Total Number Of Records : " + l.size());
			Iterator it = l.iterator();
			List<Double> li = new ArrayList<Double>();

			while (it.hasNext()) {
				Double o = (Double) it.next();
				
				li.add(o);
				
			}
			
			
			Double max = li
				      .stream()
				      .mapToDouble(v->v)
				      .max().orElseThrow(NoSuchElementException::new);
			Double min = li
				      .stream()
				      .mapToDouble(v->v)
				      .min().orElseThrow(NoSuchElementException::new);
			
			Double avg = li
				      .stream()
				      .mapToDouble(v->v)
				      .average().orElseThrow(NoSuchElementException::new);
			
			stockPriceEntity.setAverageStockPrice(avg);
			stockPriceEntity.setMaxStockPrice(max);
			stockPriceEntity.setMinStockPrice(min);
			stockPriceEntity.setStockPrice(li);
			
			return stockPriceEntity;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public Void deleteStock(String companyCode) {
		Session session = null;
		try {
			session = sessionFactory.openSession();
			session.beginTransaction();
			/*Stock stock = session.get(Stock.class, companyCode);
			session.delete(stock);*/
			Query query = session.createQuery("delete Stock where companyCode = :companyCode");
			query.setParameter("companyCode", companyCode);
			 
			int result = query.executeUpdate();
			session.getTransaction().commit();
			//return stock;
		} catch (Exception e) {
			e.printStackTrace();
		}

		return null;
	}

}
