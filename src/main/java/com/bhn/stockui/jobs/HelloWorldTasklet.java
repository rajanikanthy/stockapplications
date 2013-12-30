package com.bhn.stockui.jobs;

import java.util.Calendar;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.core.StepContribution;
import org.springframework.batch.core.scope.context.ChunkContext;
import org.springframework.batch.core.step.tasklet.Tasklet;
import org.springframework.batch.repeat.RepeatStatus;

public class HelloWorldTasklet implements Tasklet {

	private static final Logger logger = LoggerFactory.getLogger(HelloWorldTasklet.class);
	
	public RepeatStatus execute(StepContribution contrib, ChunkContext context)
			throws Exception {
		logger.info("Job says ... Hello @ {} " , Calendar.getInstance().getTime());
		return RepeatStatus.FINISHED;
	}

}
