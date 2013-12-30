package com.bhn.stockapp.yahoo;

import java.util.Calendar;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations="./historicquoteextractor-test.xml")
public class HistoricQuoteExtractorTest implements ApplicationContextAware{

	private ApplicationContext applicationContext;
	protected static Logger LOG = LoggerFactory.getLogger(HistoricQuoteExtractorTest.class);
	
	@Test
	public void testGetHistory() {
		HistoricQuoteExtractor historicQuoteExtractor = applicationContext.getBean(HistoricQuoteExtractor.class);
		Calendar endCal = Calendar.getInstance();
		Calendar startCal = Calendar.getInstance();
		startCal.add(Calendar.DATE, -30);
		LOG.info("############## First Call #####################");
		historicQuoteExtractor.getHistory("GOOG", startCal.getTime(), endCal.getTime());
		Long start = System.currentTimeMillis();
		while ( System.currentTimeMillis() - start < 5000 ) {
			try {
				LOG.info("Thread Sleep " + (System.currentTimeMillis() - start));
				Thread.sleep(1000);
				LOG.info("Thread awake");
			} catch (Exception e) {
				// do nothing
			}
		}
		LOG.info("############## Second Call #####################");
		historicQuoteExtractor.getHistory("GOOG", startCal.getTime(), endCal.getTime());
	}

	public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
	   this.applicationContext = applicationContext;	    
    }

}
