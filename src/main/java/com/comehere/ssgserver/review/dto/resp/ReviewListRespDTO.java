package com.comehere.ssgserver.review.dto.resp;

import java.util.List;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class ReviewListRespDTO {
	private String itemCode;

	private List<Long> reviews;

	private Boolean hasNext;
}
