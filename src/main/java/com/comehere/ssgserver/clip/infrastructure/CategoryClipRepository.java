package com.comehere.ssgserver.clip.infrastructure;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import com.comehere.ssgserver.clip.domain.CategoryClip;

public interface CategoryClipRepository extends JpaRepository<CategoryClip, Long>, CustomCategoryClipRepository {
	Boolean existsByUuidAndBigCategoryIdAndMiddleCategoryIdAndSmallCategoryId(
			UUID uuid,
			Long bigCategoryId,
			Long middleCategoryId,
			Long smallCategoryId);
}
