package com.mansereok.librarysearch.service;

import com.mansereok.librarysearch.entity.DailyStat;
import com.mansereok.librarysearch.repository.DailyStatRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
public class DailyStatCommandService {

	private final DailyStatRepository dailyStatRepository;

	@Transactional
	public void save(DailyStat dailyStat) {
		log.info("[DailyStatCommandService.save] dailyStat: {}", dailyStat);
		dailyStatRepository.save(dailyStat);
	}
}
