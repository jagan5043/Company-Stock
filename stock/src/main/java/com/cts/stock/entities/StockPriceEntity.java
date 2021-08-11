package com.cts.stock.entities;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Component;

@Component
public class StockPriceEntity {

	private List<Double> stockPrice = new ArrayList<Double>();
	private Double maxStockPrice;
	private Double minStockPrice;
	private Double averageStockPrice;

	public StockPriceEntity(List<Double> stockPrice, Double maxStockPrice, Double minStockPrice,
			Double averageStockPrice) {
		super();
		this.stockPrice = stockPrice;
		this.maxStockPrice = maxStockPrice;
		this.minStockPrice = minStockPrice;
		this.averageStockPrice = averageStockPrice;
	}

	public StockPriceEntity() {
		super();
	}

	public List<Double> getStockPrice() {
		return stockPrice;
	}

	public void setStockPrice(List<Double> stockPrice) {
		this.stockPrice = stockPrice;
	}

	public Double getMaxStockPrice() {
		return maxStockPrice;
	}

	public void setMaxStockPrice(Double maxStockPrice) {
		this.maxStockPrice = maxStockPrice;
	}

	public Double getMinStockPrice() {
		return minStockPrice;
	}

	public void setMinStockPrice(Double minStockPrice) {
		this.minStockPrice = minStockPrice;
	}

	public Double getAverageStockPrice() {
		return averageStockPrice;
	}

	public void setAverageStockPrice(Double averageStockPrice) {
		this.averageStockPrice = averageStockPrice;
	}

}
