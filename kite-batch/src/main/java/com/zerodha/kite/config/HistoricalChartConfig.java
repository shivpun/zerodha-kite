package com.zerodha.kite.config;

import java.util.List;

import org.springframework.batch.core.Job;
import org.springframework.batch.core.JobParameters;
import org.springframework.batch.core.JobParametersInvalidException;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.batch.core.repository.JobExecutionAlreadyRunningException;
import org.springframework.batch.core.repository.JobInstanceAlreadyCompleteException;
import org.springframework.batch.core.repository.JobRestartException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

import com.zerodha.kite.domain.ChartData;
import com.zerodha.kite.domain.OHLC;
import com.zerodha.kite.processor.HistoricalChartProcessor;
import com.zerodha.kite.reader.HistoricalChartReader;
import com.zerodha.kite.writer.HistoricalChartWriter;

//@Component(value="histChart")
public class HistoricalChartConfig {
	
	@Autowired
	private JobLauncher jobLauncher;
	
	@Autowired
	private JobBuilderFactory jobBuilderFactory;
	
	@Autowired
	private StepBuilderFactory stepBuilderFactory;
	
	@Bean(name="jobHistorical")
	public Job historicalOHLCJob() {
		return jobBuilderFactory.get("jobHistoricalHOLC")
								.flow(historicalOHLCStep())
								.build()
								.build();
	}
	
	@Bean
	public Step historicalOHLCStep() {
		return stepBuilderFactory.get("stepHistoricalHOLC")
								 .<ChartData, List<OHLC>>chunk(1)
								 .reader(new HistoricalChartReader())
								 .processor(new HistoricalChartProcessor())
								 .writer(new HistoricalChartWriter())
								 .build();
	}
	
	//@Bean
	public Job dy() throws JobExecutionAlreadyRunningException, JobRestartException, JobInstanceAlreadyCompleteException, JobParametersInvalidException {
		start();
		return null;
	}
	
	public void start() throws JobExecutionAlreadyRunningException, JobRestartException, JobInstanceAlreadyCompleteException, JobParametersInvalidException {
		jobLauncher.run(historicalOHLCJob(), new JobParameters());
	}
}
