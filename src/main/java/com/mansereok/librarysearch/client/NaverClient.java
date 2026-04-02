package com.mansereok.librarysearch.client;

import com.mansereok.librarysearch.dto.NaverBookSearchResponse;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestClient;

@Component
public class NaverClient {

	private final RestClient restClient;

	public NaverClient(
		@Value("${naver.base-url}") String baseUrl,
		@Value("${naver.client-id}") String clientId,
		@Value("${naver.client-secret}") String clientSecret
	) {
		this.restClient = RestClient.builder()
			.baseUrl(baseUrl)
			.defaultHeader("X-Naver-Client-Id", clientId)
			.defaultHeader("X-Naver-Client-Secret", clientSecret)
			.build();
	}

	public NaverBookSearchResponse searchBooks(String query, int display, int start){
		return restClient.get()
			.uri("/v1/search/book.json?query={query}&display={display}&start={start}", query, display, start)
			.retrieve()
			.body(NaverBookSearchResponse.class);
	}
}
