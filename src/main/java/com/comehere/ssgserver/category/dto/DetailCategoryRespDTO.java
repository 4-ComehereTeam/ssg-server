package com.comehere.ssgserver.category.dto;

import java.util.List;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class DetailCategoryRespDTO {
	private Integer smallCategoryId;

	private List<CategoryDTO> detailCategories;
}
