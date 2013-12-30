package com.bhn.stockapp.yahoo;

import org.springframework.batch.item.file.mapping.FieldSetMapper;
import org.springframework.batch.item.file.transform.FieldSet;
import org.springframework.util.StringUtils;
import org.springframework.validation.BindException;

import com.bhn.stockapp.SharedContext;

public class QuoteFieldSetMapper implements FieldSetMapper<Quote> {
	
	private long currentMillis = System.currentTimeMillis();
	
	public Quote mapFieldSet(FieldSet fieldSet) throws BindException {
		Quote quote = new Quote();
		quote.setUid(currentMillis);
		quote.setSymbol(readString(fieldSet,0));
		quote.setSecurityName(readString(fieldSet,1));
		quote.setCurrentPrice(readFloat(fieldSet,2));
		quote.setFetchDate(readString(fieldSet,3));
		quote.setFetchTime(readString(fieldSet,4));
		quote.setDayOpen(readFloat(fieldSet,5));
		quote.setDayHigh(readFloat(fieldSet,6));
		quote.setDayLow(readFloat(fieldSet,7));
		quote.setDividend(readFloat(fieldSet,8));
		quote.setPeRatio(readFloat(fieldSet,9));
		quote.setChangePercentage(readPercentage(fieldSet,10));
		quote.setFiftyWeekHigh(readFloat(fieldSet,12));
		quote.setFiftyWeekLow(readFloat(fieldSet, 11));
		quote.setVolume(readLong(fieldSet,13));
		quote.setExchange(readString(fieldSet,14));
		return quote;
	}
	
	public String readString(FieldSet fieldSet, int index) {
		String value = fieldSet.readString(index);
		if ( value == null || !StringUtils.hasText(value) || value.equals("N/A")){
			value = "";
		} 
		return value;
	}
	
	public float readFloat(FieldSet fieldSet, int index) {
		String value = fieldSet.readString(index);
		if ( value == null || !StringUtils.hasText(value) || value.equals("N/A")){
			value = "";
		} 
		return StringUtils.hasText(value) ? Float.parseFloat(value) : 0;
	}
	
	public Integer readInt(FieldSet fieldSet, int index) {
		String value = fieldSet.readString(index);
		if ( value == null || !StringUtils.hasText(value) || value.equals("N/A")){
			value = "";
		} 
		return StringUtils.hasText(value) ? Integer.parseInt(value) : 0;
	}
	
	public Long readLong(FieldSet fieldSet, int index) {
		String value = fieldSet.readString(index);
		if ( value == null || !StringUtils.hasText(value) || value.equals("N/A")){
			value = "";
		} 
		return StringUtils.hasText(value) ? Long.parseLong(value) : 0;
	}
	
	public float readPercentage(FieldSet fieldSet, int index) {
		String value = fieldSet.readString(index);
		float percentage = 0f;
		if ( value == null || !StringUtils.hasText(value) || value.equals("N/A")){
			value = "";
			percentage = 0f;
		} else if ( StringUtils.hasText(value)) {
			if (StringUtils.endsWithIgnoreCase(value, "%")) {
				value = value.substring(0, value.length() - 2);
			}
			percentage = Float.parseFloat(value);
		}
		return percentage;
	}

}
