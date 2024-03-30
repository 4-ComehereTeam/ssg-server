package com.comehere.ssgserver.review.dto.req;

import java.util.List;

import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class ReviewCreateReqDTO {
	private String itemCode;

	private String signinId;

	private Short star;

	private String content;

	private Long purchaseListId;

	private List<ReviewImageReqDTO> images;
}
