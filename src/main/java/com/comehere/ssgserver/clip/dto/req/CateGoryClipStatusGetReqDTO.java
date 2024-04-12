package com.comehere.ssgserver.clip.dto.req;

import lombok.Builder;
import lombok.Getter;

@Getter
public class CateGoryClipStatusGetReqDTO {
	private Long bigCategoryId;

	private Long middleCategoryId;

	private Long smallCategoryId;

	@Builder
	public CateGoryClipStatusGetReqDTO(Long bigCategoryId, Long middleCategoryId, Long smallCategoryId) {
		this.bigCategoryId = bigCategoryId;
		this.middleCategoryId = middleCategoryId;
		this.smallCategoryId = smallCategoryId;
	}
}
