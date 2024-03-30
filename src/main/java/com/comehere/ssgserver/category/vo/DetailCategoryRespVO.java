package com.comehere.ssgserver.category.vo;

import java.util.List;

import com.comehere.ssgserver.category.dto.CategoryDTO;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class DetailCategoryRespVO {
	private Integer smallCategoryId;

	private List<CategoryDTO> detailCategories;
}
