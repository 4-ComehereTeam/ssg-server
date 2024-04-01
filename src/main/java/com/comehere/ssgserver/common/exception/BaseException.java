package com.comehere.ssgserver.common.exception;

import com.comehere.ssgserver.common.response.BaseResponseStatus;

import lombok.Getter;

@Getter
public class BaseException extends RuntimeException{
	private BaseResponseStatus status;

	public BaseException(BaseResponseStatus status) {
		this.status = status;
	}

}
