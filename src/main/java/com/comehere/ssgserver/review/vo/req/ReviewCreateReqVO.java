package com.comehere.ssgserver.review.vo.req;

import java.util.List;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
public class ReviewCreateReqVO {
	private Long purchaseListId;

	private String itemCode;

	private String signinId;

	private Short star;

	private String content;

	private List<ReviewImageReqVO> images;
}
