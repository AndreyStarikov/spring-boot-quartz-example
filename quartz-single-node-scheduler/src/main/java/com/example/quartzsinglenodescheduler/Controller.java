package com.example.quartzsinglenodescheduler;

import org.quartz.JobBuilder;
import org.quartz.SchedulerException;
import org.quartz.SimpleScheduleBuilder;
import org.quartz.TriggerBuilder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.quartz.SchedulerFactoryBean;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;


@RestController
public class Controller {

  private static final Logger log = LoggerFactory.getLogger(Controller.class);

  private final SchedulerFactoryBean schedulerFactoryBean;

  public Controller(SchedulerFactoryBean schedulerFactoryBean) {
    this.schedulerFactoryBean = schedulerFactoryBean;
  }

  @GetMapping("/job/{jobName}")
  public void scheduleTask(@PathVariable String jobName) throws SchedulerException {
    log.info("Job {} is scheduling", jobName);
    var jobDetail = JobBuilder.newJob(JobExample.class)
        .usingJobData(JobExample.JOB_NAME, jobName)
        .build();
    var trigger = TriggerBuilder.newTrigger()
        .forJob(jobDetail.getKey().getName())
        .startNow()
        .withSchedule(SimpleScheduleBuilder.simpleSchedule()).build();
    schedulerFactoryBean.getScheduler().scheduleJob(jobDetail, trigger);
  }
}
