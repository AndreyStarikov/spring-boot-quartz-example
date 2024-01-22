package com.example.quartzclusterscheduler;

import static org.quartz.SimpleScheduleBuilder.simpleSchedule;

import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import org.quartz.DisallowConcurrentExecution;
import org.quartz.Job;
import org.quartz.JobBuilder;
import org.quartz.JobDetail;
import org.quartz.JobExecutionContext;
import org.quartz.Trigger;
import org.quartz.TriggerBuilder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class StaticJobExampleConfig {

  private static final String STATIC_JOB_IDENTITY_KEY = "STATIC_JOB";

  @Bean
  public JobDetail jobDetail() {
    return JobBuilder.newJob().ofType(StaticJobExampleJob.class)
        .storeDurably()
        .withIdentity(STATIC_JOB_IDENTITY_KEY)
        .build();
  }

  @Bean
  public Trigger trigger(
      @Qualifier("jobDetail") JobDetail jobDetail,
      @Value("${scheduler.staticJobIntervalInMilliseconds}") long intervalInMillis
  ) {
    return TriggerBuilder.newTrigger().forJob(jobDetail)
        .withIdentity(STATIC_JOB_IDENTITY_KEY)
        .withSchedule(
            simpleSchedule()
                .repeatForever()
                .withIntervalInMilliseconds(intervalInMillis)
                .withMisfireHandlingInstructionIgnoreMisfires())
        .build();
  }

  @DisallowConcurrentExecution
  public static class StaticJobExampleJob implements Job {

    private static final Logger log = LoggerFactory.getLogger(StaticJobExampleJob.class);
    private static final DateTimeFormatter dtf = DateTimeFormatter.ofPattern("HH:mm:ss");

    @Override
    public void execute(final JobExecutionContext context) {
      log.info("Static job is running. Current time is {}", LocalTime.now().format(dtf));
    }
  }
}
