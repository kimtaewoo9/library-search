package com.mansereok.librarysearch.controller;

import com.mansereok.librarysearch.dto.NaverBookSearchResponse;
import com.mansereok.librarysearch.service.BookService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class BookController {

	private final BookService bookService;

	public NaverBookSearchResponse search(
		@RequestParam String query,
		@RequestParam(defaultValue = "1") int page,
		@RequestParam(defaultValue = "10") int size
	){
		return bookService.searchBook(query, page, size);
	}
}
