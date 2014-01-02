package com.bhn.stockappui.dao;

import java.util.Collection;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;

import com.bhn.stockapp.yahoo.Quote;
import com.bhn.stockappui.model.quotes.TrackerBO;

public class TrackerManager {
	
	private static Logger logger = LoggerFactory.getLogger(TrackerManager.class);
	
	@Autowired
	private TrackerDAO trackerDao;
	
	@Autowired
	private JdbcTemplate jdbcTemplate;
	
	public Collection<TrackerBO> getTopQuotes() {
		logger.info("Getting top quotes...");
		return trackerDao.getTopQuotes(jdbcTemplate);
	}
	
	public Collection<TrackerBO> getTopQuotesLessThanDollar() {
		logger.info("Getting top quotes less than dollar...");
		return trackerDao.getTopQuotesLessThanDollar(jdbcTemplate);
	}
	
	public Collection<TrackerBO> getTopLosers() {
		logger.info("Getting top quotes less than dollar...");
		return trackerDao.getTopLosers(jdbcTemplate);
	}
	
	public Collection<TrackerBO> getOTCTopQuotes() {
		logger.info("Getting top quotes less than dollar...");
		return trackerDao.getTopOTCQuotes(jdbcTemplate);
	}
	
	public Collection<Quote> getTopQuotePastNDays(int nDays) {
		logger.info("Getting top quotes last {} days...", nDays);
		return trackerDao.getTopQuotePastNDays(jdbcTemplate, nDays);
	}
	
}
