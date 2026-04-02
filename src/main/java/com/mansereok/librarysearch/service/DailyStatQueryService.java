package com.mansereok.librarysearch.service;

import com.mansereok.librarysearch.controller.response.StatResponse;
import com.mansereok.librarysearch.repository.DailyStatRepository;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
public class DailyStatQueryService {

	private static final int PAGE = 0;
	private static final int SIZE = 5;

	private final DailyStatRepository dailyStatRepository;

	public StatResponse findQueryCount(String query, LocalDate date) {
		// 해당 날짜의 검색 횟수
		long count = dailyStatRepository.countByQueryAndEventDateTimeBetween(
			query,
			date.atStartOfDay(),
			date.atTime(LocalTime.MAX)
		);
		return new StatResponse(query, count);
	}

	public List<StatResponse> findTop5Query() {
		Pageable pageable = PageRequest.of(PAGE, SIZE);
		return dailyStatRepository.findTopQuery(pageable);
	}
}
