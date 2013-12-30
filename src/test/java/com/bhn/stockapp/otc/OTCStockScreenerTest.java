package com.bhn.stockapp.otc;

import java.util.Collection;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.testng.Assert;

import com.bhn.stockapp.webdriver.OTCQuote;
import com.bhn.stockapp.webdriver.SeleniumExtractor;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations="./otcquoteextractor-test.xml")
public class OTCStockScreenerTest implements ApplicationContextAware {
	
	private ApplicationContext applicationContext;
	protected static Logger LOG = LoggerFactory.getLogger(OTCStockScreenerTest.class);

	public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
		this.applicationContext = applicationContext;
    }
	
	@Test
	public void testExtract() {
		LOG.info("################## Strating test at : " + System.currentTimeMillis());
		SeleniumExtractor<OTCQuote> seleniumExtractor = applicationContext.getBean(OTCQuoteExtractor.class);
		try {
			Collection<OTCQuote> otcquotes = seleniumExtractor.extract();
			Assert.assertNotNull(otcquotes);
			Assert.assertTrue(otcquotes.size() > 0);
			for(OTCQuote otc : otcquotes) {
				LOG.info(otc.toString());
			}
		} catch (Exception e) {
			Assert.fail(e.getMessage());
		}
		
	}

}
