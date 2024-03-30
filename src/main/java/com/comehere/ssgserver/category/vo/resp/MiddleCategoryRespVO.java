package com.comehere.ssgserver.category.vo.resp;

import java.util.List;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class MiddleCategoryRespVO {
	private Integer bigCategoryId;

	private List<CategoryVO> middleCategories;
}
