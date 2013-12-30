package com.bhn.stockappui.services;

import java.util.Collection;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import com.bhn.stockapp.webdriver.OTCQuote;
import com.bhn.stockappui.dao.OTCTrackerManager;
import com.bhn.stockappui.dao.TrackerManager;
import com.bhn.stockappui.model.quotes.TrackerBO;

public class OTCTrackerService {
	private static Logger logger = LoggerFactory.getLogger(OTCTrackerService.class);
	
	@Autowired
	private OTCTrackerManager otcTrackerManager;
	
	public Collection<OTCQuote> getTopQuotes() {
		logger.info("Contacting tracker manager to get top quotes...");
		return otcTrackerManager.getTopQuotes();
	}
	
}
