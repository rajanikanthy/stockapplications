package com.bhn.stockappui.services;

import java.util.Collection;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import com.bhn.stockapp.yahoo.Quote;
import com.bhn.stockappui.dao.ETFTrackerManager;

public class ETFTrackerService {
	private static Logger logger = LoggerFactory.getLogger(ETFTrackerService.class);
	
	@Autowired
	private ETFTrackerManager etfTrackerManager;
	
	public Collection<Quote> getTopQuotes() {
		logger.info("Contacting tracker manager to get top quotes...");
		return etfTrackerManager.getTopQuotes();
	}
	
}
