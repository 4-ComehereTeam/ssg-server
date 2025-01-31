package com.comehere.ssgserver.category.dto.resp;

import java.util.List;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class SmallCategoryRespDTO {
	private Integer middleCategoryId;

	private List<CategoryDTO> smallCategories;
}
