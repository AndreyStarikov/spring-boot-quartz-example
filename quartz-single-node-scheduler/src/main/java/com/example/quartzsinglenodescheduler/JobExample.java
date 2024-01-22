package com.example.quartzsinglenodescheduler;

import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class JobExample implements Job {

  public static final String JOB_NAME = "JOB_NAME";
  private static final Logger log = LoggerFactory.getLogger(JobExample.class);

  @Override
  public void execute(final JobExecutionContext context) {
    var jobDataMap = context.getJobDetail().getJobDataMap();
    var jobName = jobDataMap.getString(JOB_NAME);
    log.info("Job {} is running", jobName);
  }
}
