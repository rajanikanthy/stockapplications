package com.bhn.stockappui.controllers;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.bhn.stockapp.webdriver.OTCQuote;
import com.bhn.stockappui.model.quotes.TrackerBO;
import com.bhn.stockappui.services.OTCTrackerService;

@Controller
@RequestMapping("/otc")
public class OTCQuoteController {
	@Autowired
	private OTCTrackerService otcTrackerService;
	
	@RequestMapping(value = "/topquotes", method=RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody Collection<OTCQuote> topOTCQuotes() {
		return otcTrackerService.getTopQuotes();
	}

}
