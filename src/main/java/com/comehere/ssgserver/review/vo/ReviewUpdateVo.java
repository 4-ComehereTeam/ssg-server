package com.comehere.ssgserver.review.vo;

import lombok.Getter;

@Getter
public class ReviewUpdateVo {
	private Long reviewId;
	private Short star;
	private Short taste;
	private Short boxing;
	private Short life;
	private String content;
}
