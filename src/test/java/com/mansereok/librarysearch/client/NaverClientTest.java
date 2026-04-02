package com.mansereok.librarysearch.client;

import com.mansereok.librarysearch.exception.ExternalApiException;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.client.RestClientTest;
import org.springframework.test.web.client.ExpectedCount;
import org.springframework.test.web.client.MockRestServiceServer;

import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.springframework.test.web.client.match.MockRestRequestMatchers.requestTo;
import static org.springframework.test.web.client.response.MockRestResponseCreators.*;

@RestClientTest(NaverClient.class)
class NaverClientTest {

	@Autowired
	private NaverClient naverClient;

	@Autowired
	private MockRestServiceServer mockServer;

	@Test
	@DisplayName("네이버 API가 500 에러를 반환하면 ExternalApiException이 발생한다")
	void search_server_error() {
		// given - 4회 재시도 모두 실패
		mockServer.expect(ExpectedCount.times(4),
				requestTo(org.hamcrest.Matchers.containsString("/v1/search/book.json")))
			.andRespond(withServerError());

		// when & then
		assertThatThrownBy(() -> naverClient.searchBooks("자바", 1, 1))
			.isInstanceOf(ExternalApiException.class)
			.hasMessageContaining("4회 재시도 후 실패");
	}

	@Test
	@DisplayName("네이버 API가 401 에러를 반환하면 ExternalApiException이 발생한다")
	void search_unauthorized() {
		// given - 4회 재시도 모두 실패
		mockServer.expect(ExpectedCount.times(4),
				requestTo(org.hamcrest.Matchers.containsString("/v1/search/book.json")))
			.andRespond(withUnauthorizedRequest());

		// when & then
		assertThatThrownBy(() -> naverClient.searchBooks("자바", 1, 1))
			.isInstanceOf(ExternalApiException.class)
			.hasMessageContaining("4회 재시도 후 실패");
	}
}
