package com.bhn.stockappui.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Collection;
import java.util.List;

import org.apache.commons.lang.NotImplementedException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;

import com.bhn.stockapp.webdriver.OTCQuote;
import com.bhn.stockappui.model.quotes.TrackerBO;

public class OTCTrackerDAO implements Tracker<OTCQuote> {

	private static final Logger logger = LoggerFactory.getLogger(OTCTrackerDAO.class);

	private static final StringBuffer SQL_OTC_TOP_QUOTES = new StringBuffer("SELECT * FROM STOCK_OTC_QUOTES WHERE UID = ( SELECT MAX(UID) FROM STOCK_OTC_QUOTES ) ORDER BY `CHANGE` DESC LIMIT 20");
	
	public Collection<OTCQuote> getTopQuotes(JdbcTemplate jdbcTemplate) {
		logger.info("Getting >>>>>>>>> TOP10_OTC_QUOTES <<<<<<<<<<<< ...");
		logger.info("Executing query {} ", SQL_OTC_TOP_QUOTES.toString());
		Collection<OTCQuote> topQuotes = jdbcTemplate.query(SQL_OTC_TOP_QUOTES.toString(), new BeanPropertyRowMapper<OTCQuote>(OTCQuote.class));
		return topQuotes;
	}

	public Collection<OTCQuote> getTopQuotesLessThanDollar(JdbcTemplate jdbcTemplate) {
		throw new NotImplementedException();
	}

	
}
