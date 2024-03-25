package com.comehere.ssgserver.category.application;

import com.comehere.ssgserver.category.dto.BigCategoryRespDTO;
import com.comehere.ssgserver.category.dto.DetailCategoryRespDTO;
import com.comehere.ssgserver.category.dto.MiddleCategoryRespDTO;
import com.comehere.ssgserver.category.dto.SmallCategoryRespDTO;

public interface CategoryService {
	BigCategoryRespDTO findBigCategory();

	MiddleCategoryRespDTO findMiddleCategory(Integer id);

	SmallCategoryRespDTO findSmallCategory(Integer id);

	DetailCategoryRespDTO findDetailCategory(Integer id);
}
