package com.comehere.ssgserver.item.dto.req;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class ItemCountReqDTO {
	private Integer bigCategoryId;

	private Integer middleCategoryId;

	private Integer smallCategoryId;

	public static ItemCountReqDTO toBuild(
			Integer bigCategoryId,
			Integer middleCategoryId,
			Integer smallCategoryId
	) {
		return ItemCountReqDTO.builder()
				.bigCategoryId(bigCategoryId)
				.smallCategoryId(smallCategoryId)
				.middleCategoryId(middleCategoryId)
				.build();
	}
}
