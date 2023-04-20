package com.example.quartzclusterscheduler;

import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

public class DynamicJobExample implements Job {

  public static final String JOB_NAME = "JOB_NAME";
  public static final String DYNAMIC_JOB_IDENTITY_KEY = "DYNAMIC_JOB_";
  private static final Logger log = LoggerFactory.getLogger(DynamicJobExample.class);

  @Override
  public void execute(final JobExecutionContext context) {
    var jobDataMap = context.getJobDetail().getJobDataMap();
    var jobName = jobDataMap.getString(JOB_NAME);
    log.info("Dynamic job {} is running", jobName);
  }
}
