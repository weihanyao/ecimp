package com.cmsz.mc.ecimp.batch.test_3;

import java.util.Date;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.JobParametersBuilder;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.StopWatch;

/** 
 * 定时调度类 
 * @author Fu Wei 
 * 
 */  
@Component("quartzLedgerJob")
public class QuartzLedgerJob {
	private static final Logger LOG = LoggerFactory.getLogger(QuartzLedgerJob.class);
	@Autowired
	private JobLauncher jobLauncher;
	
	
	@Autowired
	private Job ledgerJob;
	@Autowired
	JobParametersBuilder jobParameterBulider;
	
	
	public JobLauncher getJobLauncher() {
		return jobLauncher;
	}
	public void setJobLauncher(JobLauncher jobLauncher) {
		this.jobLauncher = jobLauncher;
	}
	public Job getLedgerJob() {
		return ledgerJob;
	}
	public void setLedgerJob(Job ledgerJob) {
		this.ledgerJob = ledgerJob;
	}
	public JobParametersBuilder getJobParameterBulider() {
		return jobParameterBulider;
	}
	public void setJobParameterBulider(JobParametersBuilder jobParameterBulider) {
		this.jobParameterBulider = jobParameterBulider;
	}
	private static long counter = 0l;
	/** 
     * 执行业务方法 
     * @throws Exception 
     */  
	public void execute()throws Exception {
		LOG.debug("start...");
		StopWatch sw = new StopWatch();
		sw.start();
		System.out.println("定时开始>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>");
		/* 
         * Spring Batch Job同一个job instance，成功执行后是不允许重新执行的【失败后是否允许重跑， 
         * 可通过配置Job的restartable参数来控制，默认是true】，如果需要重新执行，可以变通处理， 
         * 添加一个JobParameters构建类,以当前时间作为参数，保证其他参数相同的情况下却是不同的job instance 
         */  
		jobParameterBulider.addDate("date", new Date());
		jobLauncher.run(ledgerJob, jobParameterBulider.toJobParameters());
		sw.stop();
		System.out.println("定时结束>>>>>>>>>>>>>>>>>>>>>>>");
		LOG.debug("Time elapsed:{},Execute quartz ledgerJob:{}",sw.prettyPrint(),++counter);
	}
}
