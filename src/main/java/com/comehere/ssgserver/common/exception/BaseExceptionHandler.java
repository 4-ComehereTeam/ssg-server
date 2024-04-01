package com.comehere.ssgserver.common.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.comehere.ssgserver.common.response.BaseResponse;
import com.comehere.ssgserver.common.response.BaseResponseStatus;
import com.fasterxml.jackson.databind.ser.Serializers;

import lombok.extern.slf4j.Slf4j;

@RestControllerAdvice
@Slf4j
public class BaseExceptionHandler {

	@ExceptionHandler
	public ResponseEntity<?> baseException(BaseException e) {
		BaseResponse<?> response = new BaseResponse<>(e.getStatus());
		log.info("e={}", e.getStatus());
		return new ResponseEntity<>(response, response.httpStatus());
	}

	@ExceptionHandler
	public ResponseEntity<?> failToLogin(BadCredentialsException e) {
		BaseResponse<?> response = new BaseResponse<>(BaseResponseStatus.FAILED_TO_LOGIN);
		return new ResponseEntity<>(response, response.httpStatus());
	}
}
