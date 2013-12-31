package com.bhn.stockappui.controllers;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.bhn.stockapp.webdriver.OTCQuote;
import com.bhn.stockapp.yahoo.Quote;
import com.bhn.stockappui.model.quotes.TrackerBO;
import com.bhn.stockappui.services.ETFTrackerService;
import com.bhn.stockappui.services.OTCTrackerService;

@Controller
@RequestMapping("/etf")
public class ETFQuoteController {
	@Autowired
	private ETFTrackerService etfTrackerService;
	
	@RequestMapping(value = "/topquotes", method=RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody Collection<Quote> topOTCQuotes() {
		return etfTrackerService.getTopQuotes();
	}

}
