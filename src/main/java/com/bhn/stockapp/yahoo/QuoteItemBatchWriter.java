package com.bhn.stockapp.yahoo;

import java.util.ArrayList;
import java.util.List;

import org.springframework.batch.item.ItemWriter;

import com.bhn.stockapp.ftp.StockSymbolBO;

public class QuoteItemBatchWriter implements ItemWriter<StockSymbolBO> {

	private QuoteExtractor quoteExtractor;
	
	public void write(List<? extends StockSymbolBO> stocks) throws Exception {
		List<StockSymbolBO> stockSymbols = new ArrayList<StockSymbolBO>();
		stockSymbols.addAll(stocks);
		quoteExtractor.generateFile(stockSymbols);
	}
	
	public void setQuoteExtractor(QuoteExtractor quoteExtractor) {
		this.quoteExtractor = quoteExtractor;
	}

}
