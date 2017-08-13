package com.cmsz.mc.ecimp.batch.test_3;

import java.io.FileNotFoundException;

import org.springframework.batch.core.Job;
import org.springframework.batch.core.JobExecution;
import org.springframework.batch.core.JobParametersBuilder;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/** 
 * 启动定时调度 
 * @author Fu Wei 
 * 
 */  
/*ApplicationContext context = new ClassPathXmlApplicationContext("spring-batch-simple1.xml");
JobLauncher launcher = (JobLauncher) context.getBean("jobLauncher");

Job job = (Job) context.getBean("helloWorldJob");
JobParametersBuilder jobParametersBuilder = new JobParametersBuilder();
try {
	JobExecution result = launcher.run(job, jobParametersBuilder.toJobParameters());
	System.out.println(result.getJobId());
} catch (Exception e) {
	e.printStackTrace();
}*/
public class StartQuartz {
	public static void main(String[] args) throws FileNotFoundException {  
		//new ClassPathXmlApplicationContext("/com.batch/quartz-context.xml");
       ApplicationContext context = new ClassPathXmlApplicationContext("/com.batch/quartz-context.xml");//加载配置文件(包括定时任务)
       
       //单独调用读写任务
       JobLauncher launcher = (JobLauncher) context.getBean("jobLauncher");
       Job job = (Job) context.getBean("ledgerJob");
       JobParametersBuilder jobParametersBuilder = new JobParametersBuilder();
       
       try {
		JobExecution result = launcher.run(job, jobParametersBuilder.toJobParameters());
	} catch (Exception e) {
		e.printStackTrace();
	}
       
    }   
}
