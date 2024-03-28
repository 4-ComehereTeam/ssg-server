package com.comehere.ssgserver.item.vo;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class ItemListReqVO {
	private Integer bigCategoryId = 1;

	private Integer middleCategoryId;

	private Integer smallCategoryId;

	private Integer detailCategoryId;
}
