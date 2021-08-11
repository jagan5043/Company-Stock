package com.cts.stock.repository;

import java.sql.Date;
import java.util.List;

import org.springframework.stereotype.Repository;

import com.cts.stock.entities.Stock;
import com.cts.stock.entities.StockPriceEntity;

@Repository
public interface StockDAO {

	public Stock addStock(Stock stock);

	public StockPriceEntity getStock(String companyCode, Date startDate, Date endDate);

	public Void deleteStock(String companyCode);

}
