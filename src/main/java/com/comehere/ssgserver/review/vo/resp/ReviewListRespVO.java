package com.comehere.ssgserver.review.vo.resp;

import java.util.List;

import lombok.Getter;

@Getter
public class ReviewListRespVO {
	private List<Long> reviews;

	private Integer currentPage;

	private Boolean hasNext;
}
