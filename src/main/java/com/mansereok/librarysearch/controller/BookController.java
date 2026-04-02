package com.mansereok.librarysearch.controller;

import com.mansereok.librarysearch.controller.response.StatResponse;
import com.mansereok.librarysearch.dto.NaverBookSearchResponse;
import com.mansereok.librarysearch.dto.SearchRequest;
import com.mansereok.librarysearch.service.BookService;
import jakarta.validation.Valid;
import java.time.LocalDate;
import java.util.List;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@Slf4j
public class BookController {

	private final BookService bookService;

	@GetMapping("/api/v1/books")
	public NaverBookSearchResponse search(@Valid SearchRequest searchRequest) {
		return bookService.searchBooks(
			searchRequest.getQuery(),
			searchRequest.getPage(),
			searchRequest.getSize()
		);
	}

	@GetMapping("/api/v1/stats/keywords")
	public StatResponse getKeywordCount(
		@RequestParam String query,
		@RequestParam @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate date
	){
		log.info("[BookController.getKeywordCount] query: {}, date: {}", query, date);
		return bookService.findQueryCount(query, date);
	}

	@GetMapping("/api/v1/stats/ranking")
	public List<StatResponse> getTop5Stats(){
		return bookService.findTop5Query();
	}
}
