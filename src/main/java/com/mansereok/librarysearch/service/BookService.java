package com.mansereok.librarysearch.service;

import com.mansereok.librarysearch.client.NaverClient;
import com.mansereok.librarysearch.controller.response.StatResponse;
import com.mansereok.librarysearch.dto.NaverBookSearchResponse;
import com.mansereok.librarysearch.service.event.SearchEvent;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
public class BookService {

	private final NaverClient naverClient;
	private final ApplicationEventPublisher eventPublisher;

	private final DailyStatQueryService dailyStatQueryService;

	public NaverBookSearchResponse searchBooks(String query, int page, int size) {
		int start = (page - 1) * size + 1;
		NaverBookSearchResponse response = naverClient.searchBooks(query, size, start);
		if(response != null){
			log.info("검색 결과 개수: {}", response.getTotal());
			eventPublisher.publishEvent(new SearchEvent(query, LocalDateTime.now()));
		}
		return response;
	}

	public StatResponse findQueryCount(String query, LocalDate date) {
		return dailyStatQueryService.findQueryCount(query, date);
	}

	public List<StatResponse> findTop5Query() {
		return dailyStatQueryService.findTop5Query();
	}
}
