package com.comehere.ssgserver.category.application;

import java.util.List;

import org.springframework.stereotype.Service;

import com.comehere.ssgserver.category.domain.BigCategory;
import com.comehere.ssgserver.category.dto.BigCategoryRespDTO;
import com.comehere.ssgserver.category.dto.CategoryDTO;
import com.comehere.ssgserver.category.dto.DetailCategoryRespDTO;
import com.comehere.ssgserver.category.dto.MiddleCategoryRespDTO;
import com.comehere.ssgserver.category.dto.SmallCategoryRespDTO;
import com.comehere.ssgserver.category.infrastructure.BigCategoryRepository;
import com.comehere.ssgserver.category.infrastructure.DetailCategoryRepository;
import com.comehere.ssgserver.category.infrastructure.MiddleCategoryRepository;
import com.comehere.ssgserver.category.infrastructure.SmallCategoryRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class CategoryServiceImpl implements CategoryService {
	private final BigCategoryRepository bigCategoryRepository;
	private final MiddleCategoryRepository middleCategoryRepository;
	private final SmallCategoryRepository smallCategoryRepository;
	private final DetailCategoryRepository detailCategoryRepository;

	@Override
	public BigCategoryRespDTO findBigCategory() {
		List<BigCategory> bigCategories = bigCategoryRepository.findAll();

		return BigCategoryRespDTO.builder()
				.count(bigCategories.size())
				.bigCategories(bigCategories)
				.build();
	}

	@Override
	public MiddleCategoryRespDTO findMiddleCategory(Integer bigCategoryId) {
		return MiddleCategoryRespDTO.builder()
				.bigCategoryId(bigCategoryId)
				.middleCategories(middleCategoryRepository.findByBigCategoryId(bigCategoryId).stream()
						.map(CategoryDTO::new)
						.toList())
				.build();
	}

	@Override
	public SmallCategoryRespDTO findSmallCategory(Integer middleCategoryId) {
		return SmallCategoryRespDTO.builder()
				.middleCategoryId(middleCategoryId)
				.smallCategories(smallCategoryRepository.findByMiddleCategoryId(middleCategoryId).stream()
						.map(CategoryDTO::new)
						.toList())
				.build();
	}

	@Override
	public DetailCategoryRespDTO findDetailCategory(Integer smallCategoryId) {
		return DetailCategoryRespDTO.builder()
				.smallCategoryId(smallCategoryId)
				.detailCategories(detailCategoryRepository.findBySmallCategoryId(smallCategoryId).stream()
						.map(CategoryDTO::new)
						.toList())
				.build();
	}
}
