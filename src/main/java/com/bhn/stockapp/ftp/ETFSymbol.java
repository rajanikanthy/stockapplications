package com.bhn.stockapp.ftp;

public class ETFSymbol implements Symbol {
	private int id;
	private String symbol;
	private String etfName;
	private String productCategory;
	private String marketMaker;
	
	public ETFSymbol(){
		
	}
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getSymbol() {
		return symbol;
	}
	public void setSymbol(String symbol) {
		this.symbol = symbol;
	}
	public String getEtfName() {
		return etfName;
	}
	public void setEtfName(String eftName) {
		this.etfName = eftName;
	}
	public String getProductCategory() {
		return productCategory;
	}
	public void setProductCategory(String productCategory) {
		this.productCategory = productCategory;
	}
	public String getMarketMaker() {
		return marketMaker;
	}
	public void setMarketMaker(String marketMaker) {
		this.marketMaker = marketMaker;
	}
	
	
}
