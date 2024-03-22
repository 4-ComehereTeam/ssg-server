package com.comehere.ssgserver.category.infrastructure;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.comehere.ssgserver.category.domain.DetailCategory;

public interface DetailCategoryRepository extends JpaRepository<DetailCategory, Integer> {
	List<DetailCategory> findBySmallCategoryId(Integer id);
}
