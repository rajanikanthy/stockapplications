package com.bhn.stockapp.otc;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Collection;
import java.util.List;

import org.eclipse.jetty.util.log.Log;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.core.StepContribution;
import org.springframework.batch.core.scope.context.ChunkContext;
import org.springframework.batch.core.step.tasklet.Tasklet;
import org.springframework.batch.repeat.RepeatStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BatchPreparedStatementSetter;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.simple.SimpleJdbcTemplate;

import com.bhn.stockapp.webdriver.OTCQuote;
import com.bhn.stockapp.webdriver.SeleniumExtractor;
import com.thoughtworks.selenium.SeleniumException;

public class OTCQuoteTasklet implements Tasklet {

	protected static final Logger logger = LoggerFactory.getLogger(OTCQuoteTasklet.class);

	@Autowired
	private JdbcTemplate jdbcTemplate;

	@Autowired
	private SeleniumExtractor<OTCQuote> extractor;

	public RepeatStatus execute(StepContribution contrib, ChunkContext context) throws Exception {
		logger.info("Task started at {} " + System.currentTimeMillis());
		insertQuotes(extractor.extract());
		return RepeatStatus.FINISHED;

	}

	public void insertQuotes(final List<OTCQuote> otcQuotes) {
		final Long uid = System.currentTimeMillis();
		String sql = " INSERT INTO STOCK_OTC_QUOTES(`uid`, `symbol`, `security_name`, `tier`, `price`, `change`, `volume`,`security_type`,`locale`) values (?,?,?,?,?,?,?,?,?) ";
		jdbcTemplate.batchUpdate(sql, new BatchPreparedStatementSetter() {

			public void setValues(PreparedStatement ps, int i) throws SQLException {
				OTCQuote quote = otcQuotes.get(i);
				ps.setLong(1, uid);
				ps.setString(2, quote.getSymbol());
				ps.setString(3, quote.getSecurityName());
				ps.setString(4, quote.getTier());
				ps.setDouble(5, quote.getPrice());
				ps.setDouble(6, quote.getChange());
				ps.setDouble(7, quote.getVolume());
				ps.setString(8, quote.getSecurityType());
				ps.setString(9, quote.getLocale());
			}

			public int getBatchSize() {
				return otcQuotes.size();
			}
		});
	}
	
	public void setJdbcTemplate(JdbcTemplate jdbcTemplate) {
		this.jdbcTemplate = jdbcTemplate;
	}
	
	public void setExtractor(SeleniumExtractor<OTCQuote> extractor) {
		this.extractor = extractor;
	}

}
