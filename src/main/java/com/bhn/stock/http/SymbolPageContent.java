package com.bhn.stock.http;

public class SymbolPageContent {
	private String dollarChange;
	private String percentChange;
	private String symbol;
	private boolean isBullish = true;
	
	
	public String getDollarChange() {
		return dollarChange;
	}
	public void setDollarChange(String dollarChange) {
		this.dollarChange = dollarChange;
	}
	public String getPercentChange() {
		return percentChange;
	}
	public void setPercentChange(String percentChange) {
		this.percentChange = percentChange;
	}
	public boolean isBullish() {
		return isBullish;
	}
	public void setBullish(boolean isBullish) {
		this.isBullish = isBullish;
	}
	public String getSymbol() {
		return symbol;
	}
	public void setSymbol(String symbol) {
		this.symbol = symbol;
	}
	

}
