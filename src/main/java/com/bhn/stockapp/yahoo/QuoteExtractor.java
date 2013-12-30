package com.bhn.stockapp.yahoo;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.util.Calendar;
import java.util.Iterator;
import java.util.List;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import com.bhn.stockapp.ftp.StockSymbolBO;
import com.bhn.stockapp.ftp.Symbol;
import com.bhn.stockapp.webdriver.DriverConfiguration;

public class QuoteExtractor<T extends Symbol> {
	
	final static Logger LOG = LoggerFactory.getLogger(QuoteExtractor.class);
	private static final int LIMIT = 99;
	private static final String EXTN = ".csv";
	@Autowired
	private DriverConfiguration driverConfiguration;
	
	private String inputFile;
	private String outputDirectory;
	private String filePrefix;
	private String url;
	private int fetchSize;
	
	public String getInputFile(){
		return inputFile;
	}
	
	public void setInputFile(String inputFile) {
		this.inputFile = inputFile;
	}
	
	public void setOutputDirectory(String outputDirectory) {
		this.outputDirectory = outputDirectory;
	}
	
	public String getOutputDirectory() {
		return outputDirectory;
	}
	
	public void setFilePrefix(String filePrefix) {
		this.filePrefix = filePrefix;
	}
	
	public String getFilePrefix(){
		return this.filePrefix;
	}
	
	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}
	
	public int getFetchSize(){
		return fetchSize;
	}
	
	public void setFetchSize(int fetchSize) {
		this.fetchSize = fetchSize;
	}

	public void generateFile(List<Symbol> symbols) throws Exception {
		
		String outputFilePath = getOutputDirectory() + generateOutputFilePath() + EXTN;
		try {
			StringBuffer s = new StringBuffer();
			int lc = 0;
			Iterator<Symbol> iterator = symbols.iterator();
			while ( iterator.hasNext() ){
				  Symbol symbol = iterator.next();
				  s.append(symbol.getSymbol()).append(",");
				  lc++;
				  if ( lc == getFetchSize() ) {
					  writeQuotes(s, outputFilePath);
					  lc = 0;
					  s = new StringBuffer();
				  }
			  }
			  if ( lc > 0 ) {
				  writeQuotes(s,outputFilePath);  
			  }
			
		} catch (Exception e) {
			 LOG.error(e.getMessage(),e);
		} 
	}
	
	public void generateFile() throws Exception {
		LOG.info("###  Generating CSV file with input file : {} " , getInputFile());
		FileReader fileReader = null;
		BufferedReader bufferedReader = null;
		String outputFilePath = getOutputDirectory() + generateOutputFilePath() + EXTN;
		try {
			fileReader = new FileReader(new File(inputFile));
			bufferedReader = new BufferedReader(fileReader);
			String line = null;
			StringBuffer s = new StringBuffer();
			int lc = 0;
			while ( ( line = bufferedReader.readLine() )!= null){
				  s.append(line.trim()).append(",");
				  lc++;
				  if ( lc == LIMIT ) {
					  writeQuotes(s, outputFilePath);
					  lc = 0;
					  s = new StringBuffer();
				  }
			  }
			  if ( lc > 0 ) {
				  writeQuotes(s,outputFilePath);  
			  }
			
		} catch (Exception e) {
			 LOG.error(e.getMessage(),e);
		} finally {
			if ( bufferedReader != null ) {
				bufferedReader.close();
			} 
			if ( fileReader != null ){
				fileReader.close();
			}
				
		}
		
		
	}
	
	private String generateOutputFilePath() {
		return String.format("%s-%d", getFilePrefix() , Calendar.getInstance().getTimeInMillis());
	}
	
	private void writeQuotes(StringBuffer s, String filePath) throws IOException, ClientProtocolException, FileNotFoundException{
		  HttpClient httpClient = new DefaultHttpClient();
		  LOG.debug("### getting data for stock quotes: {} ", s.toString() );
		  HttpGet httpget = new HttpGet(String.format(getUrl(), s.toString()));
		  HttpResponse response = httpClient.execute(httpget);
		  HttpEntity entity = response.getEntity();
		  InputStream is = entity.getContent();
		  
		  FileOutputStream fos = new FileOutputStream(new File(filePath), true);
		  int inByte;
		  while ((inByte = is.read()) != -1) fos.write(inByte);
		  fos.close();
	}

	public void process(String absolutePath) throws Exception {
		setInputFile(absolutePath);
		generateFile();
		
	}
}
