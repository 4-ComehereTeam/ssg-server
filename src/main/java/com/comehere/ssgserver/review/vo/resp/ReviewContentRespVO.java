package com.comehere.ssgserver.review.vo.resp;

import java.time.LocalDateTime;

import lombok.Getter;

@Getter
public class ReviewContentRespVO {
	private Long reviewId;

	private String signinId;

	private String content;

	private Short star;

	private LocalDateTime createAt;
}
