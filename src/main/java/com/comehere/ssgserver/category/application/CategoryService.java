package com.comehere.ssgserver.category.application;

import java.util.List;

import com.comehere.ssgserver.category.domain.BigCategory;
import com.comehere.ssgserver.category.domain.MiddleCategory;
import com.comehere.ssgserver.category.dto.MiddleCategoryRespDTO;

public interface CategoryService {
	List<BigCategory> findBigCategory();

	MiddleCategoryRespDTO findMiddleCategory(Integer id);
}
