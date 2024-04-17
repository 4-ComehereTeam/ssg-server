package com.comehere.ssgserver.category.infrastructure;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.comehere.ssgserver.category.domain.SmallCategory;

public interface SmallCategoryRepository extends JpaRepository<SmallCategory, Integer> {
	List<SmallCategory> findByMiddleCategoryId(Integer id);
}
