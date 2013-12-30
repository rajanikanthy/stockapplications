package com.bhn.stockapp.ftp;

import org.springframework.batch.item.file.mapping.FieldSetMapper;
import org.springframework.batch.item.file.transform.FieldSet;
import org.springframework.util.StringUtils;
import org.springframework.validation.BindException;

public class StockSymbolFieldSetMapper implements FieldSetMapper<StockSymbolBO> {

	public StockSymbolBO mapFieldSet(FieldSet fieldSet) throws BindException {
		StockSymbolBO stockSymbol = new StockSymbolBO();
		if ( fieldSet.readString(0).contains(":")) {
			return null;
		}
		stockSymbol.setSymbol(fieldSet.readString(0));
		stockSymbol.setSecurityName(fieldSet.readString(1));
		stockSymbol.setMarketCategory(fieldSet.readString(2));
		stockSymbol.setTestIssue(fieldSet.readString(3));
		stockSymbol.setFinancialStatus(fieldSet.readString(4));
		String roundLotSize = fieldSet.readString(4);
		if ( roundLotSize != null && StringUtils.hasText(roundLotSize)) {
			stockSymbol.setRoundLotSize(fieldSet.readInt(5));
		} else {
			stockSymbol.setRoundLotSize(-1);
		}
		
		return stockSymbol;
	}

}
