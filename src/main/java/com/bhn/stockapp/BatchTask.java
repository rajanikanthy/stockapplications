package com.bhn.stockapp;

import java.util.Calendar;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.JobExecution;
import org.springframework.batch.core.JobParameters;
import org.springframework.batch.core.JobParametersBuilder;
import org.springframework.batch.core.launch.support.SimpleJobLauncher;
import org.springframework.batch.core.repository.JobRepository;
import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.core.task.SyncTaskExecutor;

public class BatchTask implements ApplicationContextAware, Runnable {

	protected static final Logger logger = LoggerFactory.getLogger(BatchTask.class);
	private ApplicationContext applicationContext;
	
	protected void runJob()	{
		Job job = applicationContext.getBean("nasdaqTodb", Job.class);
		JobRepository repository = applicationContext.getBean("jobRepository",JobRepository.class);
		SimpleJobLauncher jobLauncher = new SimpleJobLauncher();
		jobLauncher.setJobRepository(repository);
		SyncTaskExecutor taskExecutor = new SyncTaskExecutor();
		jobLauncher.setTaskExecutor(taskExecutor);
		logger.info("Job started at " + Calendar.getInstance().getTime());
		JobParameters params = new JobParametersBuilder().addDate("startDate", Calendar.getInstance().getTime()).toJobParameters();
		try {
			long start = System.currentTimeMillis();
			SharedContext context  = SharedContext.getInstance();
			context.refresh();
			JobExecution jobExecution = jobLauncher.run(job, params);
			while(jobExecution.getStatus().isRunning());
			Job importQuotes = applicationContext.getBean("importQuotes", Job.class);
			if ( !jobExecution.getStatus().isUnsuccessful()) {
				jobExecution = jobLauncher.run(importQuotes, params);
			}
			logger.info("Job completed in " + (System.currentTimeMillis() - start)	+ " ms");
			
		} catch (Throwable t) {
			logger.error(t.getMessage(), t);
		} 		
	}

	public void setApplicationContext(ApplicationContext applicationContext)	throws BeansException {
		this.applicationContext = applicationContext;
	}

	public void run() {
		runJob();
		
	}
	
}
