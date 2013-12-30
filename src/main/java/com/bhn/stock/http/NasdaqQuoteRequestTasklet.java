package com.bhn.stock.http;

import java.io.FileWriter;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Map;

import java_cup.runtime.Symbol;

import org.apache.commons.lang.StringUtils;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.htmlunit.HtmlUnitDriver;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.core.StepContribution;
import org.springframework.batch.core.scope.context.ChunkContext;
import org.springframework.batch.core.step.tasklet.Tasklet;
import org.springframework.batch.repeat.RepeatStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;

import au.com.bytecode.opencsv.CSVWriter;

import com.gargoylesoftware.htmlunit.BrowserVersion;
import com.mysql.jdbc.log.LogFactory;

public class NasdaqQuoteRequestTasklet implements Tasklet {

	private static final Logger		logger						= LoggerFactory
																		.getLogger(NasdaqQuoteRequestTasklet.class);

	private static final String		SQL_TOP_GAINERS_10					= "select symbol from stock_quotes where uid = ? and (exchange is null or exchange = '' or exchange in ('PCX','NGM','NasdaqNM','NIM','NCM','Other OTC','AMEX','NYSE')) and day_open > 1 and day_high < 5 order by change_percentage desc limit 10";
	private static final String		SQL_MAX_UID_DAY				= "select max(uid) from stock_quotes where created_date > date_sub(current_timestamp, interval 30 minute )";
	private static final String		SQL_INS_STOCK_QUOTE_TRACKER	= "INSERT INTO `stocks`.`stock_quote_tracker` "
																		+ "(`uid`,`symbol`,`dollar_change`,`percent_change`) VALUES (?,?,?,?)";

	private JdbcTemplate			jdbcTemplate;
	private NasdaqQuoteDataRequest	request;
	private String					outputFile;

	public RepeatStatus execute(StepContribution contribution,
			ChunkContext chunkContext) throws Exception {
		SimpleDateFormat dateFormat = new SimpleDateFormat("MMddyyHHmmss");
		String currentDate = dateFormat
				.format(Calendar.getInstance().getTime());
		//WebDriver driver = new FirefoxDriver();
		WebDriver driver = new HtmlUnitDriver(BrowserVersion.CHROME_16);
		//CSVWriter writer = new CSVWriter(new FileWriter(this.outputFile));
		List<SymbolPageContent> topQuotes = new ArrayList<SymbolPageContent>();
		try {
			for (String symbol : getTopSymbols()) {
				logger.info("### Fetching data for symbol {} ", symbol);
				SymbolPageContent content = request
						.getQuoteData(driver, symbol);
				logger.info(String.format("Quote Content [%s] - %s , %s, %s",
						symbol, content.getDollarChange(),
						content.getPercentChange(), content.isBullish()));
				String[] data = new String[] { currentDate, symbol,
						content.getDollarChange(), content.getPercentChange(),
						String.valueOf(content.isBullish()) };
				topQuotes.add(content);
				
				//writer.writeNext(data);
			}
			/* Catch exception and continue */
			try {
				writerTodb(topQuotes);
			} catch (Exception e) {
				logger.error(e.getMessage(),e);
			}
			
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			throw e;
		} finally {
			//writer.flush();
			//writer.close();
			driver.quit();
		}
		return RepeatStatus.FINISHED;
	}

	public List<String> getTopSymbols() {
		List<String> symbols = new ArrayList<String>();
		long uid = jdbcTemplate.queryForLong(SQL_MAX_UID_DAY);
		logger.info("Querying for uid => {}", uid);
		List<Map<String, Object>> rows = jdbcTemplate.queryForList(SQL_TOP_GAINERS_10,
				new Object[] { uid });
		for (Map<String, Object> row : rows) {
			for (Map.Entry<String, Object> col : row.entrySet()) {
				symbols.add(col.getValue().toString());
			}
		}
		logger.info("### Top symbols {} ", symbols.toArray());
		return symbols;
	}
	
	public void writerTodb(List<SymbolPageContent> contents) {
		long uid = System.currentTimeMillis();
		if ( contents == null || contents.size() == 0) {
			return;
		}
		for( SymbolPageContent content : contents ){
			try {
				if ( content.isBullish() ) {
					Float dollarChange = Float.parseFloat(content.getDollarChange());
					Float percentChange = Float.parseFloat(StringUtils.substring(content.getPercentChange(), 0, content.getPercentChange().length()-2));
					String symbol = content.getSymbol();
					this.jdbcTemplate.update(SQL_INS_STOCK_QUOTE_TRACKER, new Object[] {uid, content.getSymbol(), dollarChange, percentChange});
				} else {
					Float dollarChange = -Float.parseFloat(content.getDollarChange());
					Float percentChange = -Float.parseFloat(StringUtils.substring(content.getPercentChange(), 0, content.getPercentChange().length()-2));
					String symbol = content.getSymbol();
					this.jdbcTemplate.update(SQL_INS_STOCK_QUOTE_TRACKER, new Object[]{uid, content.getSymbol(), dollarChange, percentChange});
				}
			} catch (Throwable t) {
				logger.error(t.getMessage(), t);
			}
			
		}
	}

	public void setJdbcTemplate(JdbcTemplate jdbcTemplate) {
		this.jdbcTemplate = jdbcTemplate;
	}

	public void setRequest(NasdaqQuoteDataRequest request) {
		this.request = request;
	}

	public void setOutputFile(String fileName) {
		this.outputFile = fileName;
	}

}
