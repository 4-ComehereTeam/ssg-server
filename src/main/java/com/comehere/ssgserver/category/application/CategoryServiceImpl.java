package com.comehere.ssgserver.category.application;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestMapping;

import com.comehere.ssgserver.category.domain.BigCategory;
import com.comehere.ssgserver.category.domain.MiddleCategory;
import com.comehere.ssgserver.category.dto.BigCategoryRespDTO;
import com.comehere.ssgserver.category.dto.MiddleCategoryDTO;
import com.comehere.ssgserver.category.dto.MiddleCategoryRespDTO;
import com.comehere.ssgserver.category.infrastructure.BigCategoryRepository;
import com.comehere.ssgserver.category.infrastructure.MiddleCategoryRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class CategoryServiceImpl implements CategoryService{
	private final BigCategoryRepository bigCategoryRepository;
	private final MiddleCategoryRepository middleCategoryRepository;

	@Override
	public List<BigCategory> findBigCategory() {
		return bigCategoryRepository.findAll();
	}

	@Override
	public MiddleCategoryRespDTO findMiddleCategory(Integer id) {
		return MiddleCategoryRespDTO.builder()
				.bigCategoryId(id)
				.middleCategories(middleCategoryRepository.findByBigCategoryId(id).stream()
						.map(MiddleCategoryDTO::new)
						.toList())
				.build();
	}
}
