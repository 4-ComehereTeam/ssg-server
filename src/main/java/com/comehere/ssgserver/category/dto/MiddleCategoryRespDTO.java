package com.comehere.ssgserver.category.dto;

import java.util.List;

import com.comehere.ssgserver.category.domain.MiddleCategory;

import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
public class MiddleCategoryRespDTO {
	private Integer bigCategoryId;
	private List<MiddleCategoryDTO> middleCategories;
}
