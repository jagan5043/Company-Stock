package com.cts.stock.service;

import java.sql.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.cts.stock.entities.Stock;
import com.cts.stock.entities.StockPriceEntity;
import com.cts.stock.repository.StockDAO;

@Component
public class StockServiceImpl implements StockService {

	@Autowired
	private StockDAO stockDAO;

	@Override
	public Stock addStock(Stock stock) {

		Stock st = stockDAO.addStock(stock);
		return st;
	}

	@Override
	public StockPriceEntity getStock(String companyCode, Date startDate, Date endDate) {
		StockPriceEntity st = stockDAO.getStock(companyCode, startDate, endDate);
		return st;
	}

	@Override
	public Void deleteStock(String companyCode) {
		stockDAO.deleteStock(companyCode);
		return null;
	}

}
