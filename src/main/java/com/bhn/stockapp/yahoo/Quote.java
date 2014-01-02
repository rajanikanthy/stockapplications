package com.bhn.stockapp.yahoo;

import java.io.Serializable;

import org.apache.commons.lang3.builder.ReflectionToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

public class Quote implements Serializable {
	private long	uid;
	private String	symbol;
	private String	securityName;
	private float	currentPrice;
	private String	fetchDate;
	private String	fetchTime;
	private float	dayOpen;
	private float	dayHigh;
	private float	dayLow;
	private float	dividend;
	private float	peRatio;
	private float	changePercentage;
	private float	fiftyWeekHigh;
	private float	fiftyWeekLow;
	private Long	volume;
	private float	adjustedClose;
	private float	dayClose;
	private String	exchange;

	public void setUid(long uid) {
		this.uid = uid;
	}

	public long getUid() {
		return this.uid;
	}

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

	public float getCurrentPrice() {
		return currentPrice;
	}

	public void setCurrentPrice(float currentPrice) {
		this.currentPrice = currentPrice;
	}

	public String getFetchDate() {
		return fetchDate;
	}

	public void setFetchDate(String fetchDate) {
		this.fetchDate = fetchDate;
	}

	public String getFetchTime() {
		return fetchTime;
	}

	public void setFetchTime(String fetchTime) {
		this.fetchTime = fetchTime;
	}

	public float getDayOpen() {
		return dayOpen;
	}

	public void setDayOpen(float dayOpen) {
		this.dayOpen = dayOpen;
	}

	public float getDayHigh() {
		return dayHigh;
	}

	public void setDayHigh(float dayHigh) {
		this.dayHigh = dayHigh;
	}

	public float getDayLow() {
		return dayLow;
	}

	public void setDayLow(float dayLow) {
		this.dayLow = dayLow;
	}

	public float getDividend() {
		return dividend;
	}

	public void setDividend(float dividend) {
		this.dividend = dividend;
	}

	public float getPeRatio() {
		return peRatio;
	}

	public void setPeRatio(float peRatio) {
		this.peRatio = peRatio;
	}

	public float getChangePercentage() {
		return changePercentage;
	}

	public void setChangePercentage(float changePercentage) {
		this.changePercentage = changePercentage;
	}

	public float getFiftyWeekHigh() {
		return fiftyWeekHigh;
	}

	public void setFiftyWeekHigh(float fiftyWeekHigh) {
		this.fiftyWeekHigh = fiftyWeekHigh;
	}

	public float getFiftyWeekLow() {
		return fiftyWeekLow;
	}

	public void setFiftyWeekLow(float fiftyWeekLow) {
		this.fiftyWeekLow = fiftyWeekLow;
	}

	public Long getVolume() {
		return volume;
	}

	public void setVolume(Long volume) {
		this.volume = volume;
	}

	public float getAdjustedClose() {
		return adjustedClose;
	}

	public void setAdjustedClose(float adjustedClose) {
		this.adjustedClose = adjustedClose;
	}

	public float getDayClose() {
		return dayClose;
	}

	public void setDayClose(float dayClose) {
		this.dayClose = dayClose;
	}

	public String getExchange() {
		return exchange;
	}

	public void setExchange(String exchange) {
		this.exchange = exchange;
	}

	public String toString() {
		return ReflectionToStringBuilder.toString(this,
				ToStringStyle.DEFAULT_STYLE);
	}
}
