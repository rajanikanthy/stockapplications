package com.bhn.stockapp.webdriver;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public class OTCQuote {
	private String symbol;
	private String securityName;
	private String tier;
	private Double price;
	private Double change;
	private Long volume;
	private String securityType;
	private String locale;
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
	public String getTier() {
		return tier;
	}
	public void setTier(String tier) {
		this.tier = tier;
	}
	public Double getPrice() {
		return price;
	}
	public void setPrice(Double price) {
		this.price = price;
	}
	public Double getChange() {
		return change;
	}
	public void setChange(Double change) {
		this.change = change;
	}
	public Long getVolume() {
		return volume;
	}
	public void setVolume(Long volume) {
		this.volume = volume;
	}
	public String getSecurityType() {
		return securityType;
	}
	public void setSecurityType(String securityType) {
		this.securityType = securityType;
	}
	public String getLocale() {
		return locale;
	}
	public void setLocale(String locale) {
		this.locale = locale;
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
