package com.comehere.ssgserver.category.dto.resp;

import java.util.List;

import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
public class MiddleCategoryRespDTO {
	private Integer bigCategoryId;

	private List<CategoryDTO> middleCategories;
}
