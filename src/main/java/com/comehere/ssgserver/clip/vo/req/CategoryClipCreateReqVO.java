package com.comehere.ssgserver.clip.vo.req;

import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class CategoryClipCreateReqVO {
	private Long bigCategoryId;

	private Long middleCategoryId;

	private Long smallCategoryId;
}
