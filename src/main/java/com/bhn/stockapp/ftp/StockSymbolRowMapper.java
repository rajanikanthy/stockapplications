package com.bhn.stockapp.ftp;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

public class StockSymbolRowMapper implements RowMapper<StockSymbolBO> {
	public StockSymbolBO mapRow(ResultSet rs, int idx) throws SQLException {
		StockSymbolBO stock = new StockSymbolBO();
		stock.setSymbol(rs.getString(1));
		stock.setSecurityName(rs.getString(2));
		stock.setMarketCategory(rs.getString(3));
		stock.setTestIssue(rs.getString(4));
		stock.setFinancialStatus(rs.getString(5));
		stock.setRoundLotSize(rs.getInt(6));
		return stock;
	}

}
