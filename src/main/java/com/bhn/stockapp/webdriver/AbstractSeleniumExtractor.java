package com.bhn.stockapp.webdriver;

import org.openqa.selenium.WebDriver;
import org.springframework.beans.factory.annotation.Autowired;

public abstract class AbstractSeleniumExtractor<T> implements SeleniumExtractor<T> {
	@Autowired
	protected WebDriver driver;
	
	
	public void setDriver(WebDriver driver) {
		this.driver = driver;
	}
	
}
