package com.mansereok.librarysearch.service;

import com.mansereok.librarysearch.client.NaverClient;
import com.mansereok.librarysearch.dto.NaverBookSearchResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class BookService {

	private final NaverClient naverClient;

	public NaverBookSearchResponse searchBook(String query, int page, int size) {
		int start = (page - 1) * size + 1;
		return naverClient.searchBooks(query, page, start);
	}
}
