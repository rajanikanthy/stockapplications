package com.bhn.stockappui.services;

import java.util.Collection;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import com.bhn.stockapp.yahoo.Quote;
import com.bhn.stockappui.dao.TrackerManager;
import com.bhn.stockappui.model.quotes.TrackerBO;

public class TrackerService {
	private static Logger logger = LoggerFactory.getLogger(TrackerService.class);
	
	@Autowired
	private TrackerManager trackerManager;
	
	public Collection<TrackerBO> getTopQuotes() {
		logger.info("Contacting tracker manager to get top quotes...");
		return trackerManager.getTopQuotes();
	}
	
	public Collection<TrackerBO> getTopQuotesLessThanDollar() {
		logger.info("Contacting tracker manager to get top quotes less than dollar...");
		return trackerManager.getTopQuotesLessThanDollar();
	}
	
	public Collection<TrackerBO> getTopLosers() {
		logger.info("Contacting tracker manager to get top losers...");
		return trackerManager.getTopLosers();
	}
	
	public Collection<TrackerBO> getTopOTCQuotes() {
		logger.info("Contacting tracker manager to get top losers...");
		return trackerManager.getOTCTopQuotes();
	}
	
	public Collection<Quote> getTopQuotePastNDays(int nDays) {
		logger.info("Contacting tracker manager to get top quotes for last {} days...", nDays);
		return trackerManager.getTopQuotePastNDays(nDays);
	}
}
