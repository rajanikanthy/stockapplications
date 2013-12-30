package com.bhn.stockappui.controllers;

import java.util.Calendar;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.bhn.stockapp.yahoo.HistoricQuoteExtractor;
import com.bhn.stockapp.yahoo.Quote;
import com.bhn.stockappui.services.TrackerService;

@Controller
public class HistoricQuoteController {
	private static final Logger LOG = LoggerFactory.getLogger(HistoricQuoteController.class);
	
	@Autowired
	private TrackerService trackerService;
	
	@Autowired
	private HistoricQuoteExtractor historicQuoteExtractor;
	
	@RequestMapping(value ="/history/quotes/{symbol}/{days}", method=RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE )
	public @ResponseBody List<Quote> getHistory(@PathVariable("symbol") String symbol, @PathVariable("days") int days) {
		LOG.info(">>>>> Getting history for symbol {} for past {} days" , symbol, days);
		Calendar endDate = Calendar.getInstance();
		Calendar startDate = Calendar.getInstance();
		startDate.add(Calendar.DATE, -days);
		endDate.add(Calendar.DATE, 1);
		return historicQuoteExtractor.getHistory(symbol, startDate.getTime(), endDate.getTime());
	}
	
	
}
