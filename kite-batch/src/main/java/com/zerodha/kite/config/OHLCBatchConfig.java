package com.zerodha.kite.config;

import java.util.List;
import java.util.Map;

import org.springframework.batch.core.Job;
import org.springframework.batch.core.JobParametersInvalidException;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.batch.core.repository.JobExecutionAlreadyRunningException;
import org.springframework.batch.core.repository.JobInstanceAlreadyCompleteException;
import org.springframework.batch.core.repository.JobRestartException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

import com.zerodha.kite.domain.OHLC;
import com.zerodha.kite.processor.OHLCProcessor;
import com.zerodha.kite.reader.OHLCJdbcReader;
import com.zerodha.kite.util.KiteBatchUtil;
import com.zerodha.kite.writer.OHLCWriter;

@Component(value="ohlc")
public class OHLCBatchConfig {
	
	@Autowired
	private JobLauncher jobLauncher;
	
	@Autowired
	private JobBuilderFactory jobBuilderFactory;
	
	@Autowired
	private StepBuilderFactory stepBuilderFactory;
	
	@Autowired
	private OHLCJdbcReader ohlcJdbcReader;
	
	@Autowired
	private OHLCProcessor ohlcProcessor;

	@Qualifier(value="jobOHLC")
	@Autowired
	private Job job;
	
	@Bean(name="jobOHLC")
	public Job jobOHLC() {
		return jobBuilderFactory.get("jobOHLCs")
								.incrementer(KiteBatchUtil.incrementer())
								.flow(stepOHLC())
								.build()
								.build();
	}
	
	@Bean
	public Step stepOHLC() {
		return stepBuilderFactory.get("stepOHLC")
								 .<List<OHLC>, List<Map<Integer, Object[]>>>chunk(1)
								 .reader(ohlcJdbcReader)
								 .processor(ohlcProcessor)
								 .writer(new OHLCWriter())
								 .build();
	}
	
	@Bean(name= {"ohlcs"})
	public OHLC dsye() throws JobExecutionAlreadyRunningException, JobRestartException, JobInstanceAlreadyCompleteException, JobParametersInvalidException {
		start();
		return null;
	}
	
	//@Scheduled(fixedRate = 2000)
	public void start() throws JobExecutionAlreadyRunningException, JobRestartException, JobInstanceAlreadyCompleteException, JobParametersInvalidException {
		jobLauncher.run(job, KiteBatchUtil.jobParameters());
	}
}
