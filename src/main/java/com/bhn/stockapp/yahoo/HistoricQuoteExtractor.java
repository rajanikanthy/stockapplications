package com.bhn.stockapp.yahoo;

import java.io.StringReader;
import java.net.URI;
import java.util.Calendar;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriTemplate;

import au.com.bytecode.opencsv.CSVReader;
import au.com.bytecode.opencsv.bean.ColumnPositionMappingStrategy;
import au.com.bytecode.opencsv.bean.CsvToBean;

public class HistoricQuoteExtractor {

	protected static Logger LOG = LoggerFactory.getLogger(HistoricQuoteExtractor.class);

	@Autowired
	private RestTemplate restTemplate;

	private String uri;

	@Cacheable(value ="stock-history", key="#symbol")
	public List<Quote> getHistory(String symbol, Date startDate, Date endDate) {
		List<Quote> quotes = Collections.EMPTY_LIST;
		try {
			String body = sendRequest(symbol, startDate, endDate);
			quotes = transformResponse(body);
		}  catch(Throwable t) {
			LOG.error(t.getMessage(),t);
		}
		return quotes;
	}

	private List<Quote> transformResponse(String body) {
	    CSVReader reader = new CSVReader(new StringReader(body), ',', '\"', 1);
		ColumnPositionMappingStrategy<Quote> mappingStrategy = new ColumnPositionMappingStrategy<Quote>();
		mappingStrategy.setType(Quote.class);
		String[] columns = new String[] {"fetchDate","dayOpen","dayHigh","dayLow","dayClose","volume","adjustedClose"};
		mappingStrategy.setColumnMapping(columns);
		CsvToBean<Quote> csv = new CsvToBean<Quote>();
        List<Quote> quotes = csv.parse(mappingStrategy, reader);
        return quotes;
    }

	private String sendRequest(String symbol, Date startDate, Date endDate) {
		LOG.info("Sending http request to get stock history : " + symbol );
	    URI encodedUri = prepareRequest(symbol, startDate, endDate);
		ResponseEntity<String> entity = restTemplate.getForEntity(encodedUri, String.class);
		String body = entity.getBody();
	    return body;
    }

	private URI prepareRequest(String symbol, Date startDate, Date endDate) {
	    UriTemplate uriTemplate = new UriTemplate(uri);
		Map<String, String> uriVariables = new HashMap<String, String>();
		uriVariables.put("symbol", symbol);
		Calendar sDate = Calendar.getInstance();
		sDate.setTime(startDate);
		uriVariables.put("sm", String.valueOf(sDate.get(Calendar.MONTH)));
		uriVariables.put("sd", String.valueOf(sDate.get(Calendar.DATE)));
		uriVariables.put("sy", String.valueOf(sDate.get(Calendar.YEAR)));
		Calendar eDate = Calendar.getInstance();
		eDate.setTime(endDate);
		uriVariables.put("em", String.valueOf(eDate.get(Calendar.MONTH)));
		uriVariables.put("ed", String.valueOf(eDate.get(Calendar.DATE)));
		uriVariables.put("ey", String.valueOf(eDate.get(Calendar.YEAR)));
		URI encodedUri = uriTemplate.expand(uriVariables);
	    return encodedUri;
    }

	public void setUri(String uri) {
		this.uri = uri.trim();
	}

}
