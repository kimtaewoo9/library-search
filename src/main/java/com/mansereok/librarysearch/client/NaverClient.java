package com.mansereok.librarysearch.client;

import com.mansereok.librarysearch.dto.NaverBookSearchResponse;
import com.mansereok.librarysearch.exception.ExternalApiException;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.client.RestClient;
import org.springframework.retry.annotation.Backoff;
import org.springframework.retry.annotation.Recover;
import org.springframework.retry.annotation.Retryable;
import org.springframework.stereotype.Component;

@Component
public class NaverClient {

	private final RestClient restClient;

	public NaverClient(
		RestClient.Builder restClientBuilder,
		@Value("${naver.base-url}") String baseUrl,
		@Value("${naver.client-id}") String clientId,
		@Value("${naver.client-secret}") String clientSecret
	) {
		this.restClient = restClientBuilder
			.baseUrl(baseUrl)
			.defaultHeader("X-Naver-Client-Id", clientId)
			.defaultHeader("X-Naver-Client-Secret", clientSecret)
			.build();
	}

	@Retryable(
		retryFor = Exception.class,
		maxAttempts = 4,
		backoff = @Backoff(delay = 500, multiplier = 2)  // 500ms → 1s → 2s → 4s
	)
	public NaverBookSearchResponse searchBooks(String query, int display, int start) {
		return restClient.get()
			.uri("/v1/search/book.json?query={query}&display={display}&start={start}", query, display, start)
			.retrieve()
			.body(NaverBookSearchResponse.class);
	}

	@Recover
	public NaverBookSearchResponse recover(Exception e, String query, int display, int start) {
		throw new ExternalApiException("네이버 도서 API 호출에 실패했습니다. (4회 재시도 후 실패): " + e.getMessage());
	}
}
