package com.comehere.ssgserver.category.infrastructure;

import org.springframework.data.jpa.repository.JpaRepository;

import com.comehere.ssgserver.category.domain.BigCategory;

public interface BigCategoryRepository extends JpaRepository<BigCategory, Integer> {
}
