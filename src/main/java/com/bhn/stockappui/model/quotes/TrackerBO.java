package com.bhn.stockappui.model.quotes;

import java.io.Serializable;

/**
 * 
 * @author Rajanikantha
 * Holds data from one top stock quote
 *
 */
public class TrackerBO implements Serializable {
	/**
	 * 
	 */
	private static final long	serialVersionUID	= 1L;
	private String symbol;
	private float currentPrice;
	private float dollarChange;
	private float percentChange;
	private float fiftyTwoWeekHigh;
	private float fiftyTwoWeekLow;
	
	public String getSymbol() {
		return symbol;
	}
	public void setSymbol(String symbol) {
		this.symbol = symbol;
	}
	public float getCurrentPrice() {
		return currentPrice;
	}
	public void setCurrentPrice(float currentPrice) {
		this.currentPrice = currentPrice;
	}
	public float getDollarChange() {
		return dollarChange;
	}
	public void setDollarChange(float dollarChange) {
		this.dollarChange = dollarChange;
	}
	public float getPercentChange() {
		return percentChange;
	}
	public void setPercentChange(float percentChange) {
		this.percentChange = percentChange;
	}
	public float getFiftyTwoWeekHigh() {
		return fiftyTwoWeekHigh;
	}
	public void setFiftyTwoWeekHigh(float fiftyTwoWeekHigh) {
		this.fiftyTwoWeekHigh = fiftyTwoWeekHigh;
	}
	public float getFiftyTwoWeekLow() {
		return fiftyTwoWeekLow;
	}
	public void setFiftyTwoWeekLow(float fiftyTwoWeekLow) {
		this.fiftyTwoWeekLow = fiftyTwoWeekLow;
	}
	
}
