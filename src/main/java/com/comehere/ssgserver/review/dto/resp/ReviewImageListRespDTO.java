package com.comehere.ssgserver.review.dto.resp;

import java.util.List;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class ReviewImageListRespDTO {
	private Long itemId;

	private List<ReviewImageDTO> images;
}
