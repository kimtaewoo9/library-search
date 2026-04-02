package com.mansereok.librarysearch.dto;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class SearchRequest {

	@NotBlank(message = "검색어는 비어있을 수 없습니다.")
	@Size(max = 50, message = "검색어는 50자를 초과할 수 없습니다.")
	private String query;

	@NotNull(message = "페이지 번호는 필수입니다.")
	@Min(value = 1, message = "페이지 번호는 1 이상이어야 합니다.")
	private Integer page = 1;

	@NotNull(message = "페이지 사이즈는 필수입니다.")
	@Min(value = 1, message = "페이지 사이즈는 1 이상이어야 합니다.")
	@Max(value = 50, message = "페이지 사이즈는 50 이하여야 합니다.")
	private Integer size = 10;
}
