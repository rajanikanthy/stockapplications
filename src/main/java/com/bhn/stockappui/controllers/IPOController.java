package com.bhn.stockappui.controllers;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.bhn.stockapp.webdriver.IPO;
import com.bhn.stockapp.webdriver.SeleniumExtractor;

@Controller
@RequestMapping("/ipo")
public class IPOController {
	
	@Autowired
	private SeleniumExtractor<IPO> upComingIPOExtractor;
	
	@RequestMapping(value = "/upcoming", method=RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody Collection<IPO> upcomingIPOs() throws Exception {
		return upComingIPOExtractor.extract();
	}

}
