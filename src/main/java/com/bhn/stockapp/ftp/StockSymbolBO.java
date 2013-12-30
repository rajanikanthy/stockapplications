package com.bhn.stockapp.ftp;

import java.io.Serializable;

public class StockSymbolBO implements Serializable, Symbol {
	/**
	 * default serial version
	 */
	private static final long serialVersionUID = 1L;
	private String symbol;
	private String securityName;
	private String marketCategory;
	private String testIssue;
	private String financialStatus;
	private int roundLotSize;
	public String getSymbol() {
		return symbol;
	}
	public void setSymbol(String symbol) {
		this.symbol = symbol;
	}
	public String getSecurityName() {
		return securityName;
	}
	public void setSecurityName(String securityName) {
		this.securityName = securityName;
	}
	public String getMarketCategory() {
		return marketCategory;
	}
	public void setMarketCategory(String marketCategory) {
		this.marketCategory = marketCategory;
	}
	public String getTestIssue() {
		return testIssue;
	}
	public void setTestIssue(String testIssue) {
		this.testIssue = testIssue;
	}
	public String getFinancialStatus() {
		return financialStatus;
	}
	public void setFinancialStatus(String financialStatus) {
		this.financialStatus = financialStatus;
	}
	public int getRoundLotSize() {
		return roundLotSize;
	}
	public void setRoundLotSize(int roundLotSize) {
		this.roundLotSize = roundLotSize;
	}
}
