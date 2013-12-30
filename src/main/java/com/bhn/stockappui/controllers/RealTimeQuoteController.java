package com.bhn.stockappui.controllers;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.bhn.stockappui.model.quotes.TrackerBO;
import com.bhn.stockappui.services.TrackerService;

@Controller
@RequestMapping("/realtime")
public class RealTimeQuoteController {
	@Autowired
	private TrackerService trackerService;
	
	@RequestMapping(value = "/morethandollar" , method= RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody Collection<TrackerBO> topQuotesMoreThanDollar() {
		return trackerService.getTopQuotes();
	}
	
	@RequestMapping(value = "/lessthandollar" , method= RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody Collection<TrackerBO> topQuotesLessThanDollar() {
		return trackerService.getTopQuotesLessThanDollar();
	}
	
	@RequestMapping(value = "/toplosers" , method= RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody Collection<TrackerBO> topLosers() {
		return trackerService.getTopLosers();
	}
	
}
