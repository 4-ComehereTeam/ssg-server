package com.comehere.ssgserver.clip.dto.req;

import lombok.Getter;

@Getter
public class CategoryClipDeleteReqDTO {
	private Long bigCategoryId;

	private Long middleCategoryId;

	private Long smallCategoryId;
}
