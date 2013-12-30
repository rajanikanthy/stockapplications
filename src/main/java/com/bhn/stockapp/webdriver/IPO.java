package com.bhn.stockapp.webdriver;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public class IPO {
	private String companyName;
	private String symbol;
	private String market;
	private String price;
	private String shares;
	private String offerAmount;
	private String expectedDate;
	
	public String getCompanyName() {
		return companyName;
	}
	public void setCompanyName(String companyName) {
		this.companyName = companyName;
	}
	public String getSymbol() {
		return symbol;
	}
	public void setSymbol(String symbol) {
		this.symbol = symbol;
	}
	public String getMarket() {
		return market;
	}
	public void setMarket(String market) {
		this.market = market;
	}
	public String getPrice() {
		return price;
	}
	public void setPrice(String price) {
		this.price = price;
	}
	public String getShares() {
		return shares;
	}
	public void setShares(String shares) {
		this.shares = shares;
	}
	public String getOfferAmount() {
		return offerAmount;
	}
	public void setOfferAmount(String offerAmount) {
		this.offerAmount = offerAmount;
	}
	public String getExpectedDate() {
		return expectedDate;
	}
	public void setExpectedDate(String expectedDate) {
		this.expectedDate = expectedDate;
	}
	
	public String toString() {
		StringBuffer buffer = new StringBuffer("[");
		for( Method method : this.getClass().getMethods()) {
			if ( method.getName().startsWith("get") && method.getParameterTypes().length == 0) {
				String attr = method.getName().substring(method.getName().indexOf("get") + 3);
				try {
					buffer.append(attr).append(":").append(method.invoke(this, null));
					buffer.append(",");
				} catch (IllegalArgumentException e) {
					e.printStackTrace();
				} catch (IllegalAccessException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (InvocationTargetException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
		buffer.substring(0, buffer.length() - 2);
		buffer.append("]");
		return buffer.toString();
	}
	
	
}
