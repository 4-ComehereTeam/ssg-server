package com.comehere.ssgserver.category.infrastructure;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.comehere.ssgserver.category.domain.SmallCategory;

public interface SmallCategoryRepository extends JpaRepository<SmallCategory, Long> {
	List<SmallCategory> findByMiddleCategoryId(Integer id);
}
