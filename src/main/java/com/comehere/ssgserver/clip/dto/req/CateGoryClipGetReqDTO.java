package com.comehere.ssgserver.clip.dto.req;

import lombok.Builder;
import lombok.Getter;

@Getter
public class CateGoryClipGetReqDTO {
	private Long bigCategoryId;

	private Long middleCategoryId;

	private Long smallCategoryId;

	@Builder
	public CateGoryClipGetReqDTO(Long bigCategoryId, Long middleCategoryId, Long smallCategoryId) {
		this.bigCategoryId = bigCategoryId;
		this.middleCategoryId = middleCategoryId;
		this.smallCategoryId = smallCategoryId;
	}
}
