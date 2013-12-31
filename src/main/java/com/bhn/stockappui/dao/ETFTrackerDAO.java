package com.bhn.stockappui.dao;

import java.util.Collection;

import org.apache.commons.lang.NotImplementedException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;

import com.bhn.stockapp.webdriver.OTCQuote;
import com.bhn.stockapp.yahoo.Quote;

public class ETFTrackerDAO implements Tracker<Quote> {

	private static final Logger			logger				= LoggerFactory
																	.getLogger(ETFTrackerDAO.class);

	private static final StringBuffer	SQL_ETF_TOP_QUOTES	= new StringBuffer(
																	"select * ")
																	.append(" from stock_etf_quotes ")
																	.append(" where uid = (select max(uid) from stock_etf_quotes) ")
																	.append(" order by change_percentage desc  ")
																	.append(" limit 20");

	public Collection<Quote> getTopQuotes(JdbcTemplate jdbcTemplate) {
		logger.info("Getting >>>>>>>>> TOP20_ETF_QUOTES <<<<<<<<<<<< ...");
		logger.info("Executing query {} ", SQL_ETF_TOP_QUOTES.toString());
		Collection<Quote> topQuotes = jdbcTemplate.query(SQL_ETF_TOP_QUOTES
				.toString().toLowerCase(), new BeanPropertyRowMapper<Quote>(
				Quote.class));
		return topQuotes;
	}

	public Collection<Quote> getTopQuotesLessThanDollar(
			JdbcTemplate jdbcTemplate) {
		throw new NotImplementedException();
	}

}
