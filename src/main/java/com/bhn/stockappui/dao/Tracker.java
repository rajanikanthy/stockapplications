package com.bhn.stockappui.dao;

import java.util.Collection;

import org.springframework.jdbc.core.JdbcTemplate;

import com.bhn.stockappui.model.quotes.TrackerBO;

public interface Tracker<T> {
	Collection<T> getTopQuotes(JdbcTemplate jdbcTemplate);
	Collection<T> getTopQuotesLessThanDollar(JdbcTemplate jdbcTemplate);
}
