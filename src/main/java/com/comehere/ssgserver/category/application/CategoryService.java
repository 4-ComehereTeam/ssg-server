package com.comehere.ssgserver.category.application;

import java.util.List;

import com.comehere.ssgserver.category.domain.BigCategory;
import com.comehere.ssgserver.category.domain.MiddleCategory;
import com.comehere.ssgserver.category.dto.BigCategoryRespDTO;
import com.comehere.ssgserver.category.dto.MiddleCategoryRespDTO;

public interface CategoryService {
	BigCategoryRespDTO findBigCategory();

	MiddleCategoryRespDTO findMiddleCategory(Integer id);
}
