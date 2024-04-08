package com.comehere.ssgserver.review.dto.resp;

import java.util.List;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class ReviewImageListRespDTO {
	private String itemCode;

	private List<ReviewImageListDTO> images;

	private Integer currentPage;

	private Boolean hasNext;
}
