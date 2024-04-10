package com.comehere.ssgserver.common.batch;

import java.time.LocalDateTime;

import org.springframework.batch.core.Job;
import org.springframework.batch.core.JobExecution;
import org.springframework.batch.core.JobParameters;
import org.springframework.batch.core.JobParametersBuilder;
import org.springframework.batch.core.JobParametersInvalidException;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.batch.core.repository.JobExecutionAlreadyRunningException;
import org.springframework.batch.core.repository.JobInstanceAlreadyCompleteException;
import org.springframework.batch.core.repository.JobRestartException;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.Scheduled;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Configuration
@RequiredArgsConstructor
public class JobScheduler {
	private final JobLauncher jobLauncher;
	private final Job job;

	@Scheduled(cron = "0 0 * * * ?")
	public void jobSchedule() throws
			JobInstanceAlreadyCompleteException,
			JobExecutionAlreadyRunningException,
			JobParametersInvalidException,
			JobRestartException {

		JobParameters jobParameters = new JobParametersBuilder()
				.addLocalDateTime("time", LocalDateTime.now())
				.toJobParameters();

		JobExecution jobExecution = jobLauncher.run(job, jobParameters);
	}
}
