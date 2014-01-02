package com.bhn.stockapp.yahoo;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Comparator;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class FetchDateComparator implements Comparator<Quote> {
	private Logger logger = LoggerFactory.getLogger(FetchDateComparator.class);
	
	public int compare(Quote q1, Quote q2) {
		int returnValue = 0;
		SimpleDateFormat sdf = new SimpleDateFormat("M/d/yyyy");
		try {
			java.util.Date q1Value = sdf.parse(q1.getFetchDate());
			java.util.Date q2Value = sdf.parse(q2.getFetchDate());
			returnValue = (q1Value.compareTo(q2Value));
		} catch (ParseException pe) {
			logger.error(pe.getMessage(), pe);
		}
		return returnValue;
	}

}
