package com.comehere.ssgserver.category.application;

import java.util.List;

import com.comehere.ssgserver.category.domain.BigCategory;

public interface CategoryService {
	List<BigCategory> findBigCategory();
}
