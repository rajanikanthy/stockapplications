package com.bhn.stock.http;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By.ById;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.htmlunit.HtmlUnitDriver;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

public class NasdaqQuoteDataRequest {
	private static final Logger logger = LoggerFactory.getLogger(NasdaqQuoteDataRequest.class);
	
	
	private String baseUrl;
		
	public NasdaqQuoteDataRequest(String url) {
		this.baseUrl = url;
	}
	
	public SymbolPageContent getQuoteData(WebDriver driver, String symbol) {
		SymbolPageContent pageContent = new SymbolPageContent();
		StringBuffer url = new StringBuffer(this.baseUrl).append("/").append(symbol);
		logger.info("### Sending request {} " , url.toString());
		driver.get(url.toString());
		//logger.info("### Response page content");
		//logger.info(driver.getPageSource());
		logger.info("### Implicit wait");
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		WebElement pElement = driver.findElement(By.id("qwidget_percent"));
		WebElement dElement = driver.findElement(By.id("qwidget_netchange"));
		
		if ( pElement.getAttribute("class").toLowerCase().contains("red")) {
			pageContent.setBullish(false);
		} else {
			pageContent.setBullish(true);
		}
		
		pageContent.setDollarChange(dElement.getText());
		pageContent.setPercentChange(pElement.getText());
		pageContent.setSymbol(symbol);
		
		return pageContent;
	}
	
	
}
