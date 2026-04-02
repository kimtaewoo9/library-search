package com.mansereok.librarysearch.service.event;

import com.mansereok.librarysearch.entity.DailyStat;
import com.mansereok.librarysearch.service.DailyStatCommandService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.event.EventListener;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
@Slf4j
public class SearchEventHandler {

	private final DailyStatCommandService dailyStatCommandService;

	@Async
	@EventListener
	public void handleEvent(SearchEvent event){
		log.info("[SearchEventHandler.handleEvent] event: {}", event);
		DailyStat dailyStat = DailyStat.create(event.query(), event.timestamp());
		dailyStatCommandService.save(dailyStat);
	}
}
