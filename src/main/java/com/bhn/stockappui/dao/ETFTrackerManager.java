package com.bhn.stockappui.dao;

import java.util.Collection;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;

import com.bhn.stockapp.webdriver.OTCQuote;
import com.bhn.stockapp.yahoo.Quote;
import com.bhn.stockappui.model.quotes.TrackerBO;

public class ETFTrackerManager {
	
	private static Logger logger = LoggerFactory.getLogger(ETFTrackerManager.class);
	
	@Autowired
	private ETFTrackerDAO etfTrackerDao;
	
	@Autowired
	private JdbcTemplate jdbcTemplate;
	
	public Collection<Quote> getTopQuotes() {
		logger.info("Getting top quotes...");
		return etfTrackerDao.getTopQuotes(jdbcTemplate);
	}
	
	
}
