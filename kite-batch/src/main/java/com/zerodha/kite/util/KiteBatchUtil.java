package com.zerodha.kite.util;

import org.springframework.batch.core.JobParameters;
import org.springframework.batch.core.JobParametersIncrementer;

public class KiteBatchUtil {
	
	public static JobParametersIncrementer incrementer() {
		JobParametersIncrementer incrementer = new JobParametersIncrementer() {
			
			@Override
			public JobParameters getNext(JobParameters parameters) {
				return jobParameters();
			}
		};
		return incrementer;
	}
	
	public static JobParameters jobParameters() {
		JobParameters jobParameters = new JobParameters();
		jobParameters.getDouble("jobId", Math.random());
		return jobParameters;
	}

}
