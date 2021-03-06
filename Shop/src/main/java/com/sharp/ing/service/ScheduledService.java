package com.sharp.ing.service;

import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
@NoArgsConstructor
public class ScheduledService {

//	@Scheduled(fixedDelay = 1000)
	public void fixedDelayJob() {
		log.info("This job is executed per a second");
	}
}
