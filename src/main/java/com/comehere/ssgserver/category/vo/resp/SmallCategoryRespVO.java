package com.comehere.ssgserver.category.vo.resp;

import java.util.List;

import com.comehere.ssgserver.category.dto.resp.CategoryDTO;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class SmallCategoryRespVO {
	private Integer middleCategoryId;

	private List<CategoryDTO> smallCategories;
}
