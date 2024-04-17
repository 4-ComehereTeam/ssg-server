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
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.transaction.annotation.Transactional;

import com.comehere.ssgserver.bundle.infrastructure.BundleRepository;
import com.comehere.ssgserver.member.infrastructure.MemberRepository;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Configuration
@RequiredArgsConstructor
public class JobScheduler {

	private final JobLauncher jobLauncher;
	private final BundleRepository bundleRepository;
	private final MemberRepository memberRepository;

	@Qualifier("itemSummaryJob")
	private final Job itemSummaryJob;

	//매일 정각마다 상품 집계 계산 후 업데이트 (배치 작업) 수행
	@Scheduled(cron = "0 0 * * * ?")
	public void jobSchedule() throws
			JobInstanceAlreadyCompleteException,
			JobExecutionAlreadyRunningException,
			JobParametersInvalidException,
			JobRestartException {

		JobParameters jobParameters = new JobParametersBuilder()
				.addLocalDateTime("time", LocalDateTime.now())
				.toJobParameters();

		JobExecution jobExecution = jobLauncher.run(itemSummaryJob, jobParameters);
	}

	// 매일 자정마다 판매 종료 기간이 지난 특가 상품의 판매 상태를 false로 변경
	@Transactional
	@Scheduled(cron = "0 0 0 * * ?")
	public void updateBundleStatus() {
		bundleRepository.updateBundleStatus();
	}

	// 메일 자정마다 마지막 활동시간이 1년이 지난 회원을 휴면회원으로 전환
	@Transactional
	@Scheduled(cron = "0 0 0 * * ?")
	public void updateDormantMember() {
		memberRepository.updateDormantMember();
	}
}
