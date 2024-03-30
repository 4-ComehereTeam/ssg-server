package com.comehere.ssgserver.category.dto.resp;

import java.util.List;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class DetailCategoryRespDTO {
	private Integer smallCategoryId;

	private List<CategoryDTO> detailCategories;
}
