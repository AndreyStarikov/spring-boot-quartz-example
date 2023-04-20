package com.example.springbootscheduler;

import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
public class Task {

  private static final Logger log = LoggerFactory.getLogger(Task.class);

  private static final DateTimeFormatter dtf = DateTimeFormatter.ofPattern("HH:mm:ss");

  @Scheduled(fixedRate = 5000)
  public void task() {
    log.info("Current time is {}", LocalTime.now().format(dtf));
  }
}
