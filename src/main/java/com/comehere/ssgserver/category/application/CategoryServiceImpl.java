package com.comehere.ssgserver.category.application;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.comehere.ssgserver.category.domain.BigCategory;
import com.comehere.ssgserver.category.domain.MiddleCategory;
import com.comehere.ssgserver.category.domain.SmallCategory;
import com.comehere.ssgserver.category.dto.resp.BigCategoryRespDTO;
import com.comehere.ssgserver.category.dto.resp.CategoryDTO;
import com.comehere.ssgserver.category.dto.resp.CategoryNameRespDTO;
import com.comehere.ssgserver.category.dto.resp.DetailCategoryRespDTO;
import com.comehere.ssgserver.category.dto.resp.MiddleCategoryRespDTO;
import com.comehere.ssgserver.category.dto.resp.SmallCategoryRespDTO;
import com.comehere.ssgserver.category.infrastructure.BigCategoryRepository;
import com.comehere.ssgserver.category.infrastructure.DetailCategoryRepository;
import com.comehere.ssgserver.category.infrastructure.MiddleCategoryRepository;
import com.comehere.ssgserver.category.infrastructure.SmallCategoryRepository;
import com.comehere.ssgserver.common.exception.BaseException;
import com.comehere.ssgserver.common.response.BaseResponseStatus;

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

	@Override
	public CategoryNameRespDTO findBigCategoryName(Integer bigCategoryId) {
		BigCategory bigCategory = bigCategoryRepository.findById(bigCategoryId)
				.orElseThrow(() -> new BaseException(BaseResponseStatus.CATEGORY_NOT_FOUND));

		return CategoryNameRespDTO.builder()
				.categoryName(bigCategory.getName())
				.build();
	}

	@Override
	public CategoryNameRespDTO findMiddleCategoryName(Integer middleCategoryId) {
		MiddleCategory middleCategory = middleCategoryRepository.findById(middleCategoryId)
				.orElseThrow(() -> new BaseException(BaseResponseStatus.CATEGORY_NOT_FOUND));

		return CategoryNameRespDTO.builder()
				.categoryName(middleCategory.getName())
				.build();
	}

	@Override
	public CategoryNameRespDTO findSmallCategoryName(Integer smallCategoryId) {
		SmallCategory smallCategory = smallCategoryRepository.findById(smallCategoryId)
				.orElseThrow(() -> new BaseException(BaseResponseStatus.CATEGORY_NOT_FOUND));

		return CategoryNameRespDTO.builder()
				.categoryName(smallCategory.getName())
				.build();
	}
}
