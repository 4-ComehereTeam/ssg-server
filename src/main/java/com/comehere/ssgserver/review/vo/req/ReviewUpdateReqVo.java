package com.comehere.ssgserver.review.vo.req;

import lombok.Getter;

@Getter
public class ReviewUpdateReqVo {
	private Long reviewId;
	private Short star;
	private String content;
}
