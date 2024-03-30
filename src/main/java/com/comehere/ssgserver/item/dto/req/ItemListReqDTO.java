package com.comehere.ssgserver.item.dto.req;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class ItemListReqDTO {
	private Integer bigCategoryId;

	private Integer middleCategoryId;

	private Integer smallCategoryId;

	private Integer detailCategoryId;

	public static ItemListReqDTO toBuild(
			Integer bigCategoryId,
			Integer middleCategoryId,
			Integer smallCategoryId,
			Integer detailCategoryId) {

		return ItemListReqDTO.builder()
				.bigCategoryId(bigCategoryId)
				.middleCategoryId(middleCategoryId)
				.smallCategoryId(smallCategoryId)
				.detailCategoryId(detailCategoryId)
				.build();
	}
}
