package com.comehere.ssgserver.item.vo;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ItemListReqVO {
	private String search;

	private Integer bigCategoryId;

	private Integer middleCategoryId;

	private Integer smallCategoryId;

}
