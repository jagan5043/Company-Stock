package com.cts.stock.service;

import java.sql.Date;
import java.util.List;

import org.springframework.stereotype.Service;

import com.cts.stock.entities.Stock;
import com.cts.stock.entities.StockPriceEntity;

@Service
public interface StockService {

	public Stock addStock(Stock stockObject);

	public StockPriceEntity getStock(String companyCode, Date startDate, Date endDate);

	public Void deleteStock(String companyCode);
	
	

}
