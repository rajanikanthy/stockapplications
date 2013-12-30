package com.bhn.stockappui.dao;

import java.util.Collection;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;

import com.bhn.stockapp.webdriver.OTCQuote;
import com.bhn.stockappui.model.quotes.TrackerBO;

public class OTCTrackerManager {
	
	private static Logger logger = LoggerFactory.getLogger(OTCTrackerManager.class);
	
	@Autowired
	private OTCTrackerDAO otcTrackerDao;
	
	@Autowired
	private JdbcTemplate jdbcTemplate;
	
	public Collection<OTCQuote> getTopQuotes() {
		logger.info("Getting top quotes...");
		return otcTrackerDao.getTopQuotes(jdbcTemplate);
	}
	
	
}
