package com.comehere.ssgserver.category.presentation;

import org.modelmapper.ModelMapper;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.comehere.ssgserver.category.application.CategoryService;
import com.comehere.ssgserver.category.vo.resp.BigCategoryRespVO;
import com.comehere.ssgserver.category.vo.resp.DetailCategoryRespVO;
import com.comehere.ssgserver.category.vo.resp.MiddleCategoryRespVO;
import com.comehere.ssgserver.category.vo.resp.SmallCategoryRespVO;
import com.comehere.ssgserver.common.response.BaseResponse;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/categories")
@Tag(name = "categories", description = "카테고리 관리 컨트롤러")
@Slf4j
public class CategoryController {
	private final CategoryService categoryService;
	private final ModelMapper modelMapper;

	@GetMapping
	@Operation(summary = "카테고리 (대) 조회 API", description = "대 카테고리 목록 조회")
	public BaseResponse<BigCategoryRespVO> getBigCategory() {
		log.info("대 카테고리 목록 조회");
		return new BaseResponse<>(modelMapper.map(categoryService.findBigCategory(), BigCategoryRespVO.class));
	}

	@GetMapping("/middle/{bigCategoryId}")
	@Operation(summary = "카테고리 (중) 조회 API", description = "대 카테고리에 해당하는 중 카테고리 목록 조회")
	public BaseResponse<MiddleCategoryRespVO> getMiddleCategory(@PathVariable("bigCategoryId") Integer id) {
		log.info("중 카테고리 목록 조회 : big={}", id);
		return new BaseResponse<>(modelMapper.map(categoryService.findMiddleCategory(id), MiddleCategoryRespVO.class));
	}

	@GetMapping("/small/{middleCategoryId}")
	@Operation(summary = "카테고리 (소) 조회 API", description = "중 카테고리에 해당하는 소 카테고리 목록 조회")
	public BaseResponse<SmallCategoryRespVO> getSmallCategory(@PathVariable("middleCategoryId") Integer id) {
		log.info("소 카테고리 목록 조회 : middle={}", id);
		return new BaseResponse<>(modelMapper.map(categoryService.findSmallCategory(id), SmallCategoryRespVO.class));
	}

	@GetMapping("/detail/{smallCategoryId}")
	@Operation(summary = "카테고리 (상세) 조회 API", description = "소 카테고리에 해당하는 상세 카테고리 목록 조회")
	public BaseResponse<DetailCategoryRespVO> getDetailCategory(@PathVariable("smallCategoryId") Integer id) {
		log.info("상세 카테고리 목록 조회 : detail={}", id);
		return new BaseResponse<>(modelMapper.map(categoryService.findDetailCategory(id), DetailCategoryRespVO.class));
	}

}
