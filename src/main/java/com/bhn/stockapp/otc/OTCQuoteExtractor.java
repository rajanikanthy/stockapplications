package com.bhn.stockapp.otc;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.collections.Predicate;
import org.apache.commons.lang.ArrayUtils;
import org.apache.commons.lang.StringUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.bhn.stockapp.webdriver.AbstractSeleniumExtractor;
import com.bhn.stockapp.webdriver.OTCQuote;

public class OTCQuoteExtractor extends AbstractSeleniumExtractor<OTCQuote> {
	private static Logger LOG = LoggerFactory.getLogger(OTCQuoteExtractor.class);
	private String uri;

	private String minPrice;
	private String maxPrice;
	private String minPercentChange;
	private String[] tiers;
	private String[] securityType;
	private String[] locale;

	public OTCQuoteExtractor(String uri) {
		this.uri = uri;
	}

	public void setMinPrice(String minPrice) {
		this.minPrice = minPrice;
	}

	public void setMaxPrice(String maxPrice) {
		this.maxPrice = maxPrice;
	}

	public void setMinPercentChange(String minPercentChange) {
		this.minPercentChange = minPercentChange;
	}

	public void setTiers(String[] tiers) {
		this.tiers = tiers;
	}

	public void setSecurityType(String[] securityType) {
		this.securityType = securityType;
	}

	public void setLocale(String[] locale) {
		this.locale = locale;
	}

	public String getUri() {
		return uri;
	}

	public List<OTCQuote> extract() throws Exception {
		return getOTCQuotes();
	}

	private List<OTCQuote> getOTCQuotes() throws Exception {
		List<OTCQuote> otcQuotes = null;
		driver.get(uri);
		try {
			driver.findElement(By.name("minPrice")).sendKeys(minPrice);
			driver.findElement(By.name("maxPrice")).sendKeys(maxPrice);
			driver.findElement(By.name("minPercentChange")).sendKeys(minPercentChange);
			select("tierBox", "tierId", tiers);
			select("secTypeBox", "securityType", securityType);
			select("localeBox", "countryId", locale);
			driver.findElement(By.id("go")).click();
			Boolean isNextLinkFound = Boolean.TRUE;
			otcQuotes = new ArrayList<OTCQuote>();
			do {
				extractData(otcQuotes);
				try {
					WebElement nextElement = driver.findElement(By.linkText("next >"));
					nextElement.click();
				} catch (Exception e) {
					isNextLinkFound = false;
				}
			} while (isNextLinkFound);

		} catch (Exception e) {
			//IOUtils.write(driver.getPageSource(),new FileOutputStream("c:/work/temp/stockapp/pagesource-" + System.currentTimeMillis()));
			LOG.error(e.getMessage(), e);
		}
		return otcQuotes;
	}

	private void extractData(List<OTCQuote> otcQuotes) throws Exception {
		WebElement listings = (new WebDriverWait(driver, 60L).until(ExpectedConditions.presenceOfElementLocated(By
		        .className("listing"))));
		List<WebElement> rowElements = listings.findElements(By.tagName("tr"));
		for (WebElement rowElem : rowElements) {
			List<WebElement> colElements = rowElem.findElements(By.tagName("td"));
			if (colElements != null && !colElements.isEmpty() && colElements.size() >= 8) {
				try {
					OTCQuote quote = new OTCQuote();
					quote.setSymbol(colElements.get(0).getText());
					quote.setSecurityName(colElements.get(1).getText());
					quote.setTier(colElements.get(2).getText());
					LOG.debug("Price: {} " , colElements.get(3).getText() );
					quote.setPrice(Double.valueOf(colElements.get(3).getText()));
					quote.setChange(Double.valueOf(colElements.get(4).getText()));
					quote.setVolume(Long.valueOf(StringUtils.remove(colElements.get(5).getText(), ",")));
					quote.setSecurityType(colElements.get(6).getText());;
					quote.setLocale(colElements.get(7).getText());
					otcQuotes.add(quote);
					LOG.debug(quote.toString());
				} catch (Exception e) {
					// Ignore data conversion exceptions
				}
				
			}
		}
	}

	private void select(String boxId, String elementId, final String[] tiersTobeSelected) {
		WebElement tierBox = driver.findElement(By.id(boxId));
		tierBox.findElement(By.linkText("None")).click();
		List<WebElement> tiers = tierBox.findElements(By.name(elementId));
		CollectionUtils.filter(tiers, new Predicate() {
			public boolean evaluate(Object o) {
				if (o instanceof WebElement) {
					WebElement elem = (WebElement) o;
					return ArrayUtils.contains(tiersTobeSelected, elem.getAttribute("desc"));
				}
				return false;
			}
		});
		for (WebElement tier : tiers) {
			tier.click();
		}

	}

}
