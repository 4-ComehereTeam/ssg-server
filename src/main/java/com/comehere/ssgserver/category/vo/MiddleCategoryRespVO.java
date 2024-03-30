package com.comehere.ssgserver.category.vo;

import java.util.List;

import com.comehere.ssgserver.category.domain.MiddleCategory;
import com.comehere.ssgserver.category.dto.CategoryDTO;
import com.comehere.ssgserver.category.dto.MiddleCategoryRespDTO;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class MiddleCategoryRespVO {
	private Integer bigCategoryId;

	private List<CategoryVO> middleCategories;
}
