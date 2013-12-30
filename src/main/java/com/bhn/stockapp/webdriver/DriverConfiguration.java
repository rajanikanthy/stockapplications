package com.bhn.stockapp.webdriver;


public class DriverConfiguration {
	private String url;
	private String username;
	private String password;
	private String financeUrl;
	private String defaultStockSymbol;
	
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getFinanceUrl() {
		return financeUrl;
	}
	public void setFinanceUrl(String financeUrl) {
		this.financeUrl = financeUrl;
	}
	public String getDefaultStockSymbol() {
		return defaultStockSymbol;
	}
	public void setDefaultStockSymbol(String defaultStockSymbol) {
		this.defaultStockSymbol = defaultStockSymbol;
	}
	
	
}
