package com.comehere.ssgserver.clip.dto.req;

import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class CategoryClipCreateReqDTO {
	private Long bigCategoryId;

	private Long middleCategoryId;

	private Long smallCategoryId;

}
