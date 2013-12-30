package com.bhn.stockapp.webdriver;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class UpcomingIPOExtractor extends AbstractSeleniumExtractor<IPO> {

	private static Logger LOG = LoggerFactory.getLogger(UpcomingIPOExtractor.class);

	private String uri;

	private static final int COMPANY_NAME_IDX = 0;
	private static final int SYMBOL_IDX = 1;
	private static final int MARKET_IDX = 2;
	private static final int PRICE_IDX = 3;
	private static final int SHARES_IDX = 4;
	private static final int OFFER_AMOUNT_IDX = 5;
	private static final int EXPECTED_IPO_DATE = 6;

	public UpcomingIPOExtractor(String uri) {
		this.uri = uri;
	}

	public List<IPO> extract() throws Exception {
		return getUpComingIPOs();
	}

	private List<IPO> getUpComingIPOs() throws Exception {
		this.driver.get(uri);
		WebElement divElement = driver.findElement(By.className("genTable"));
		List<WebElement> trElements = divElement.findElement(By.tagName("tbody")).findElements(By.tagName("tr"));
		List<IPO> ipoList = Collections.synchronizedList(new ArrayList<IPO>());
		try {
			if (trElements != null && !trElements.isEmpty()) {
				for (WebElement trElement : trElements) {
					List<WebElement> tdElements = trElement.findElements(By.tagName("td"));
					IPO ipo = new IPO();
					ipo.setCompanyName(parseCompanyName(tdElements));
					ipo.setExpectedDate(parseExpectedIPODate(tdElements));
					ipo.setMarket(parseMarket(tdElements));
					ipo.setOfferAmount(parseOfferAmount(tdElements));
					ipo.setPrice(parsePrice(tdElements));
					ipo.setShares(parseShares(tdElements));
					ipo.setSymbol(parseSymbol(tdElements));

					ipoList.add(ipo);
				}
			}
		} catch (Exception e) {
			LOG.error(e.getMessage(), e);
			throw e;
		} 

		return ipoList;
	}

	private String parseCompanyName(List<WebElement> tdElements) throws Exception {
		return (tdElements != null && !tdElements.isEmpty() && tdElements.size() > COMPANY_NAME_IDX) ? tdElements
		        .get(COMPANY_NAME_IDX).findElement(By.tagName("a")).getText() : StringUtils.EMPTY;

	}

	private String parseSymbol(List<WebElement> tdElements) throws Exception {
		return (tdElements != null && !tdElements.isEmpty() && tdElements.size() > SYMBOL_IDX) ? tdElements.get(
		        SYMBOL_IDX).getText() : StringUtils.EMPTY;
	}

	private String parseMarket(List<WebElement> tdElements) throws Exception {
		return (tdElements != null && !tdElements.isEmpty() && tdElements.size() > MARKET_IDX) ? tdElements
		        .get(COMPANY_NAME_IDX).findElement(By.tagName("a")).getText() : StringUtils.EMPTY;
	}

	private String parsePrice(List<WebElement> tdElements) throws Exception {
		return (tdElements != null && !tdElements.isEmpty() && tdElements.size() > PRICE_IDX) ? tdElements.get(
		        PRICE_IDX).getText() : StringUtils.EMPTY;
	}

	private String parseShares(List<WebElement> tdElements) throws Exception {
		return (tdElements != null && !tdElements.isEmpty() && tdElements.size() > SHARES_IDX) ? tdElements.get(
		        SHARES_IDX).getText() : StringUtils.EMPTY;
	}

	private String parseOfferAmount(List<WebElement> tdElements) throws Exception {
		return (tdElements != null && !tdElements.isEmpty() && tdElements.size() > OFFER_AMOUNT_IDX) ? tdElements.get(
		        OFFER_AMOUNT_IDX).getText() : StringUtils.EMPTY;
	}

	private String parseExpectedIPODate(List<WebElement> tdElements) throws Exception {
		return (tdElements != null && !tdElements.isEmpty() && tdElements.size() > EXPECTED_IPO_DATE) ? tdElements.get(
		        EXPECTED_IPO_DATE).getText() : StringUtils.EMPTY;
	}

}
