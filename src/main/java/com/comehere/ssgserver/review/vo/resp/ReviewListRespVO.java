package com.comehere.ssgserver.review.vo.resp;

import java.util.List;

import lombok.Getter;

@Getter
public class ReviewListRespVO {
	private String itemCode;

	private List<Long> reviews;

	private Boolean hasNext;
}
