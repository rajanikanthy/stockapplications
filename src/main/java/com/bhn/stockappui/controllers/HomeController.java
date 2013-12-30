package com.bhn.stockappui.controllers;

import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

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
}
