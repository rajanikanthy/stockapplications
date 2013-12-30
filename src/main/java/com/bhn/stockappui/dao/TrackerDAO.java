package com.bhn.stockappui.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Collection;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;

import com.bhn.stockappui.model.quotes.TrackerBO;

public class TrackerDAO implements Tracker<TrackerBO> {

	private static final Logger logger = LoggerFactory.getLogger(TrackerDAO.class);

	private static final String SQL_TOP_QUOTES = "select " + " t.symbol, " + " q.current_price, "
	        + " t.dollar_change, " + " t.percent_change, " + " q.52_week_high, " + " q.52_week_low " + " from "
	        + " stock_quote_tracker t " + "   inner join " + " stock_quotes q ON t.symbol = q.symbol " + " where "
	        + " t.uid = (select " + "         max(uid)  " + "     from  " + "         stock_quote_tracker)  "
	        + "     and q.uid = (select  " + "         max(uid) " + "     from " + "         stock_quotes) "
	        + " order by t.percent_change desc ";

	private static final StringBuffer SQL_TOP_QUOTES_LESS_THAN_DOLLAR = new StringBuffer(
	        " SELECT symbol, current_price, (day_open - current_price) dollar_change, ")
	        .append(" change_percentage as percent_change, 52_week_high, 52_week_low ")
	        .append(" FROM stocks.stock_quotes ")
	        .append(" WHERE (exchange is null or exchange = '' or exchange in ('PCX','NGM','NasdaqNM','NIM','NCM','AMEX','NYSE'))  AND ")
	        .append("	   ").append("	  current_price < 1 AND ").append("	  change_percentage > 1 AND ")
	        .append("	  uid = ( select max(uid) from stocks.stock_quotes ) ")
	        .append(" ORDER BY change_percentage DESC LIMIT 20 ");

	private static final StringBuffer SQL_TOP10_LOSERS = new StringBuffer("SELECT ")
	        .append(" symbol, current_price, (day_open - current_price) dollar_change, ")
	        .append(" change_percentage as percent_change, 52_week_high, 52_week_low ")
	        .append(" FROM ")
	        .append(" stocks.stock_quotes ")
	        .append(" WHERE ")
	        .append(" (exchange is null or exchange = '' or exchange in ('PCX','NGM','NasdaqNM','NIM','NCM','AMEX','NYSE')) AND uid = (SELECT ")
	        .append(" MAX(uid) ").append(" FROM ").append(" stock_quotes ").append(" WHERE ")
	        .append(" created_date > CURDATE()) ").append(" ORDER BY change_percentage ASC ").append(" LIMIT 10 ");

	private static final StringBuffer SQL_OTC_TOP_QUOTES = new StringBuffer(" SELECT ").append(" symbol, ")
	        .append(" current_price, ").append(" (day_open - current_price) dollar_change, ")
	        .append(" change_percentage as percent_change, ").append(" 52_week_high, ").append(" 52_week_low ")
	        .append(" FROM ").append(" stocks.stock_quotes ").append(" WHERE ")
	        .append(" (exchange not in ('PCX' , 'NGM', ").append(" 'NasdaqNM', ").append(" 'NIM', ").append(" 'NCM', ")
	        .append(" 'AMEX', ").append(" 'NYSE')) ").append(" AND current_price > 0 and current_price < 1 ")
	        .append(" AND change_percentage > 1 ").append(" AND uid = (select  ").append("     max(uid) ")
	        .append(" from ").append(" stocks.stock_quotes) ").append(" ORDER BY change_percentage DESC ")
	        .append(" LIMIT 20");

	public Collection<TrackerBO> getTopQuotes(JdbcTemplate jdbcTemplate) {
		logger.info("Getting >>>>>> TOP_QUOTES <<<<<<<<< ...");
		logger.info("Executing query {} ", SQL_TOP_QUOTES);
		Collection<TrackerBO> topQuotes = jdbcTemplate.query(SQL_TOP_QUOTES, new TrackerRowMapper());
		return topQuotes;
	}

	public Collection<TrackerBO> getTopQuotesLessThanDollar(JdbcTemplate jdbcTemplate) {
		logger.info("Getting >>>>>>>> TOP_QUOTES_LESS_THAN_DOLLAR <<<<<<<< ...");
		logger.info("Executing query {} ", SQL_TOP_QUOTES_LESS_THAN_DOLLAR);
		Collection<TrackerBO> topQuotes = jdbcTemplate.query(SQL_TOP_QUOTES_LESS_THAN_DOLLAR.toString(),
		        new TrackerRowMapper());
		return topQuotes;
	}

	public Collection<TrackerBO> getTopLosers(JdbcTemplate jdbcTemplate) {
		logger.info("Getting >>>>>>>>> TOP10_LOSERS <<<<<<<<<<<< ...");
		logger.info("Executing query {} ", SQL_TOP10_LOSERS.toString());
		Collection<TrackerBO> topQuotes = jdbcTemplate.query(SQL_TOP10_LOSERS.toString(), new TrackerRowMapper());
		return topQuotes;
	}

	public Collection<TrackerBO> getTopOTCQuotes(JdbcTemplate jdbcTemplate) {
		logger.info("Getting >>>>>>>>> TOP10_LOSERS <<<<<<<<<<<< ...");
		logger.info("Executing query {} ", SQL_OTC_TOP_QUOTES.toString());
		Collection<TrackerBO> topQuotes = jdbcTemplate.query(SQL_OTC_TOP_QUOTES.toString(), new TrackerRowMapper());
		return topQuotes;
	}

	public class TrackerRowMapper implements RowMapper<TrackerBO> {

		public TrackerBO mapRow(ResultSet rs, int it) throws SQLException {
			TrackerBO tracker = new TrackerBO();
			tracker.setSymbol(rs.getString("symbol"));
			tracker.setCurrentPrice(rs.getFloat("current_price"));
			tracker.setDollarChange(rs.getFloat("dollar_change"));
			tracker.setPercentChange(rs.getFloat("percent_change"));
			tracker.setFiftyTwoWeekHigh(rs.getFloat("52_week_high"));
			tracker.setFiftyTwoWeekLow(rs.getFloat("52_week_low"));
			return tracker;
		}

	}
}
