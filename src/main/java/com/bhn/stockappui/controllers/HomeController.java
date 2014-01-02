package com.bhn.stockappui.controllers;

import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.bhn.stockapp.yahoo.FetchDateComparator;
import com.bhn.stockapp.yahoo.Quote;
import com.bhn.stockappui.services.TrackerService;

@Controller
public class HomeController {
	
	private static final Logger logger = LoggerFactory.getLogger(HomeController.class);
	
	@Autowired
	private TrackerService trackerService;
	
	
	@RequestMapping({"/","/home"})
	public String showHomePage(Map<String,Object> model) {
		logger.info("Get top quotes ...");
		model.put("topQuotes", trackerService.getTopQuotes());
		model.put("topQuotesLessThanDollar", trackerService.getTopQuotesLessThanDollar());
		model.put("topLosers", trackerService.getTopLosers());
		return "index";
	}
	
	@RequestMapping(value = "/home/topquotes/{days}", method=RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody Collection<Quote> getQuotes(@PathVariable("days")int nDays) {
		logger.info(">>> getting data for last {} days " , nDays);
		List<Quote> quotes = (List<Quote>) trackerService.getTopQuotePastNDays(nDays);
		if ( quotes != null && quotes.isEmpty() ) {
			Collections.sort(quotes, new FetchDateComparator());
		}
		for(Quote quote : quotes) {
			logger.info(quote.toString());
		}
		return quotes;
	}
	
}
