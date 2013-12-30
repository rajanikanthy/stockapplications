package com.bhn.stockapp.ftp;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.PrintWriter;

import org.apache.commons.net.PrintCommandListener;
import org.apache.commons.net.ftp.FTP;
import org.apache.commons.net.ftp.FTPClient;
import org.apache.commons.net.ftp.FTPReply;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class StockSymbolListGenerator {
	
	protected static Logger LOG = LoggerFactory.getLogger(StockSymbolListGenerator.class);
	
	private FTPClient ftpClient;
	private String hostName;
	private String localFileName;
	private String remoteFileName;
	
	public String getHostName() {
		return hostName;
	}
	public void setHostName(String hostName) {
		this.hostName = hostName;
	}
	public String getLocalFileName() {
		return localFileName;
	}
	public void setLocalFileName(String localFileName) {
		this.localFileName = localFileName;
	}
	public String getRemoteFileName() {
		return remoteFileName;
	}
	public void setRemoteFileName(String remoteFileName) {
		this.remoteFileName = remoteFileName;
	}
	
	public void getSymbolList() throws Exception {
		FileOutputStream output = null;
		try {
			ftpClient = new FTPClient();
			ftpClient.setDefaultTimeout(60000);
			ftpClient.addProtocolCommandListener(new PrintCommandListener(new PrintWriter(System.out), true));
			ftpClient.connect(getHostName());
			int reply = ftpClient.getReplyCode();
			if ( !FTPReply.isPositiveCompletion(reply) ) {
				throw new Exception("Unable to connect");
			}
			if ( !ftpClient.login("anonymous", "abc@yahoo.com")) {
				throw new Exception("Login Failed");
			}
			ftpClient.setFileType(FTP.ASCII_FILE_TYPE);
			ftpClient.enterLocalPassiveMode();
			output = new FileOutputStream(getLocalFileName());
			LOG.info("### Receiving file : {}  ", getRemoteFileName());
			ftpClient.retrieveFile(getRemoteFileName(), output);
			output.close();
		} catch (Exception e) {
			LOG.error(e.getMessage(),e);
		} finally {
			if ( ftpClient != null && ftpClient.isConnected() ) {
				ftpClient.noop();
				ftpClient.logout();
				ftpClient.disconnect();
			}
		}
	}
}
