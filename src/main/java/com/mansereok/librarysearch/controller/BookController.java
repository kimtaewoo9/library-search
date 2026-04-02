package com.mansereok.librarysearch.controller;

import com.mansereok.librarysearch.dto.NaverBookSearchResponse;
import com.mansereok.librarysearch.dto.SearchRequest;
import com.mansereok.librarysearch.service.BookService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class BookController {

	private final BookService bookService;

	@GetMapping("/api/v1/books")
	public NaverBookSearchResponse search(@Valid SearchRequest searchRequest) {
		return bookService.searchBook(
			searchRequest.getQuery(),
			searchRequest.getPage(),
			searchRequest.getSize()
		);
	}
}
