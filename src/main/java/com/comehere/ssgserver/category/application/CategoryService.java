package com.comehere.ssgserver.category.application;

import com.comehere.ssgserver.category.dto.resp.BigCategoryRespDTO;
import com.comehere.ssgserver.category.dto.resp.CategoryNameRespDTO;
import com.comehere.ssgserver.category.dto.resp.DetailCategoryRespDTO;
import com.comehere.ssgserver.category.dto.resp.MiddleCategoryRespDTO;
import com.comehere.ssgserver.category.dto.resp.SmallCategoryRespDTO;

public interface CategoryService {
	BigCategoryRespDTO findBigCategory();

	MiddleCategoryRespDTO findMiddleCategory(Integer id);

	SmallCategoryRespDTO findSmallCategory(Integer id);

	DetailCategoryRespDTO findDetailCategory(Integer id);

	CategoryNameRespDTO findBigCategoryName(Integer bigCategoryId);

	CategoryNameRespDTO findMiddleCategoryName(Integer middleCategoryId);

	CategoryNameRespDTO findSmallCategoryName(Integer smallCategoryId);
}
