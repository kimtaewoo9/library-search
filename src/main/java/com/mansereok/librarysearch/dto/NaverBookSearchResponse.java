package com.mansereok.librarysearch.dto;

import java.time.LocalDateTime;
import java.util.List;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor // 역직렬화를 위해서 Jackson 라이브러리를 사용하는데 Jackson 라이브러리가 기본 생성자를 필요로함 .
public class NaverBookSearchResponse {
	private int total;
	private int start;
	private int display;
	private List<Item> items;

	@Getter
	@NoArgsConstructor
	public static class Item {
		private String title;
		private String link;
		private String image;
		private String author;
		private Integer discount;
		private String publisher;
		private Integer isbn;
		private String description;
		private String pubDate;
	}
}
