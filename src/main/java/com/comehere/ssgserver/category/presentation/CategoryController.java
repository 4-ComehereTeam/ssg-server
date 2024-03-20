package com.comehere.ssgserver.category.presentation;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.comehere.ssgserver.category.application.CategoryService;
import com.comehere.ssgserver.category.domain.BigCategory;
import com.comehere.ssgserver.category.domain.MiddleCategory;
import com.comehere.ssgserver.category.dto.BigCategoryRespDTO;
import com.comehere.ssgserver.category.dto.MiddleCategoryDTO;
import com.comehere.ssgserver.category.dto.MiddleCategoryRespDTO;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/categories")
@Tag(name = "categories", description = "카테고리 관리 컨트롤러")
public class CategoryController {
	private final CategoryService categoryService;

	@GetMapping
	@Operation(summary = "카테고리 (대) 조회 API", description = "대 카테고리 목록 조회")
	public BigCategoryRespDTO getBigCategory() {
		return categoryService.findBigCategory();
	}

	@GetMapping("/{id}")
	@Operation(summary = "카테고리 (중) 조회 API", description = "대 카테고리에 해당하는 중 카테고리 목록 조회")
	public MiddleCategoryRespDTO getMiddleCategory(@PathVariable("id") Integer id) {
		return categoryService.findMiddleCategory(id);
	}
}
