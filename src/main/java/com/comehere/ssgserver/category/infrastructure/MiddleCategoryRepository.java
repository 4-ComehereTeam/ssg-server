package com.comehere.ssgserver.category.infrastructure;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.comehere.ssgserver.category.domain.MiddleCategory;

public interface MiddleCategoryRepository extends JpaRepository<MiddleCategory, Integer> {
	List<MiddleCategory> findByBigCategoryId(Integer id);
}
