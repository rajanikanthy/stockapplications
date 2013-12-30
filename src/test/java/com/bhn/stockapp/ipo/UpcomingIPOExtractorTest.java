package com.bhn.stockapp.ipo;

import java.util.Collection;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.openqa.selenium.WebDriver;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.testng.Assert;

import com.bhn.stockapp.webdriver.IPO;
import com.bhn.stockapp.webdriver.SeleniumExtractor;
import com.bhn.stockapp.webdriver.UpcomingIPOExtractor;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations="./upcomingipoextractor-test.xml")
public class UpcomingIPOExtractorTest implements ApplicationContextAware {

	private ApplicationContext applicationContext;
	
	protected static Logger LOG = LoggerFactory.getLogger(UpcomingIPOExtractorTest.class);
	
	
	@Test
	public void testDriverInjection() {
		WebDriver driver = applicationContext.getBean(WebDriver.class);
		Assert.assertNotNull(driver);
	}
	
	@Test
	public void testExtraction() {
		SeleniumExtractor<IPO> seleniumExtractor = applicationContext.getBean(UpcomingIPOExtractor.class);
		try {
			Collection<IPO> ipos = seleniumExtractor.extract();
			Assert.assertNotNull(ipos);
			Assert.assertTrue(ipos.size() > 0);
			for(IPO ipo : ipos) {
				LOG.info(ipo.toString());
			}
		} catch (Exception e) {
			Assert.fail(e.getMessage());
		}
		
	}

	public void setApplicationContext(ApplicationContext applicationContext)
			throws BeansException {
		this.applicationContext = applicationContext;
	}

}
