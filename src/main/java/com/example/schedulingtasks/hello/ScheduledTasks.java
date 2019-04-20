package com.example.schedulingtasks.hello;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.concurrent.TimeUnit;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
public class ScheduledTasks {

	private static final Logger log = LoggerFactory.getLogger(ScheduledTasks.class);
	private final DateTimeFormatter  dateFormat = DateTimeFormatter.ofPattern("HH:mm:ss");
	private static final String MESSAGE = "Current Thread : {}";

	private ScheduledTasks() {
	}

	@Scheduled(fixedRate = 5000)
	public void scheduleTaskWithFixedRate() {
		log.info(MESSAGE, Thread.currentThread().getName());
		String date = dateFormat.format(LocalDateTime.now());
		log.info("The time is now {}", date);
	}

	@Scheduled(fixedDelay = 2000)
	public void scheduleTaskWithFixedDelay() {
		log.info(MESSAGE, Thread.currentThread().getName());
		String date = dateFormat.format(LocalDateTime.now());
		log.info("Fixed Delay Task :: Execution Time - {}", date);
		try {
			TimeUnit.SECONDS.sleep(5);
		} catch (InterruptedException ex) {
			log.error("Ran into an error {}", ex);
			Thread.currentThread().interrupt();
			throw new IllegalStateException(ex);
		}
	}

	@Scheduled(fixedRate = 2000, initialDelay = 5000)
	public void scheduleTaskWithInitialDelay() {
		log.info(MESSAGE, Thread.currentThread().getName());
		String date = dateFormat.format(LocalDateTime.now());
		log.info("Fixed Rate Task with Initial Delay :: Execution Time - {}", date);
	}

	@Scheduled(cron = "0 * * * * ?")
	public void scheduleTaskWithCronExpression() {
		log.info(MESSAGE, Thread.currentThread().getName());
		String date = dateFormat.format(LocalDateTime.now());
		log.info("Cron Task :: Execution Time - {}", date);
	}
}
