package com.mansereok.librarysearch.exception;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.multipart.support.MissingServletRequestPartException;

@RestControllerAdvice
public class GlobalExceptionHandler {

	@ExceptionHandler(ExternalApiException.class)
	public ResponseEntity<ErrorResponse> handleExternalApi(ExternalApiException e){
		return ResponseEntity.status(502).body(
			new ErrorResponse("EXTERNAL_API_ERROR", e.getMessage())
		);
	}

	@ExceptionHandler(MissingServletRequestParameterException.class)
	public ResponseEntity<ErrorResponse> handleMissingServletRequestParameterException(MissingServletRequestParameterException e){
		return ResponseEntity.badRequest().body(
			new ErrorResponse("MISSING_PARAMETER", e.getParameterName() + "은 필수 입니다.")
		);
	}

	@ExceptionHandler(Exception.class)
	public ResponseEntity<ErrorResponse> handleException(Exception e){
		return ResponseEntity.internalServerError().body(
			new ErrorResponse("INTERNAL_SERVER_ERROR", "서버 내부 오류가 발생했습니다.")
		);
	}
}
