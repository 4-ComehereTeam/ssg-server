package com.comehere.ssgserver.clip.presentation;

import java.util.UUID;

import org.modelmapper.ModelMapper;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.comehere.ssgserver.clip.application.CategoryClipService;
import com.comehere.ssgserver.clip.dto.req.CateGoryClipGetReqDTO;
import com.comehere.ssgserver.clip.dto.req.CategoriesClipDeleteReqDTO;
import com.comehere.ssgserver.clip.dto.req.CategoryClipCreateReqDTO;
import com.comehere.ssgserver.clip.vo.req.CategoriesClipDeleteReqVO;
import com.comehere.ssgserver.clip.vo.req.CategoryClipCreateReqVO;
import com.comehere.ssgserver.clip.vo.resp.CategoriesClipGetRespVO;
import com.comehere.ssgserver.clip.vo.resp.CategoryClipGetInfoRespVO;
import com.comehere.ssgserver.clip.vo.resp.CategoryClipGetRespVO;
import com.comehere.ssgserver.common.response.BaseResponse;
import com.comehere.ssgserver.common.security.jwt.JWTUtil;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/v1/clip")
@RequiredArgsConstructor
@Tag(name = "category clip", description = "카테고리 좋아요 컨트롤러")
public class CategoryClipController {
	private final CategoryClipService categoryClipService;

	private final JWTUtil jwtUtil;

	private final ModelMapper modelMapper;

	@Operation(summary = "카테고리 좋아요")
	@PostMapping("/category")
	public BaseResponse<Void> createCategoryClip(
			@RequestBody CategoryClipCreateReqVO vo,
			@RequestHeader("Authorization") String accessToken) {

		UUID uuid = jwtUtil.getUuidByAuthorization(accessToken);
		categoryClipService.createCategoryClip(uuid, modelMapper.map(vo, CategoryClipCreateReqDTO.class));

		return new BaseResponse<>();
	}

	@Operation(summary = "카테고리 좋아요 N개 취소")
	@DeleteMapping("/categories")
	public BaseResponse<Void> deleteCategoriesClip(
			@RequestBody CategoriesClipDeleteReqVO vo,
			@RequestHeader("Authorization") String accessToken) {

		UUID uuid = jwtUtil.getUuidByAuthorization(accessToken);
		categoryClipService.deleteCategoriesClip(uuid, modelMapper.map(vo, CategoriesClipDeleteReqDTO.class));

		return new BaseResponse<>();
	}

	@Operation(summary = "카테고리 좋아요 목록 조회")
	@GetMapping("/categories")
	public BaseResponse<CategoriesClipGetRespVO> getCategoriesClip(
			@RequestHeader("Authorization") String accessToken) {

		UUID uuid = jwtUtil.getUuidByAuthorization(accessToken);

		return new BaseResponse<>(
				modelMapper.map(categoryClipService.getCategoriesClip(uuid), CategoriesClipGetRespVO.class));
	}

	@Operation(summary = "카테고리 좋아요 조회")
	@GetMapping("/category")
	public BaseResponse<CategoryClipGetInfoRespVO> getCategoryClip(
			@RequestParam(value = "bigCategoryId", required = false) Long bigCategoryId,
			@RequestParam(value = "middleCategoryId", required = false) Long middleCategoryId,
			@RequestParam(value = "smallCategoryId", required = false) Long smallCategoryId,
			@RequestHeader("Authorization") String accessToken) {

		UUID uuid = jwtUtil.getUuidByAuthorization(accessToken);

		CateGoryClipGetReqDTO dto = CateGoryClipGetReqDTO.builder()
				.bigCategoryId(bigCategoryId)
				.middleCategoryId(middleCategoryId)
				.smallCategoryId(smallCategoryId)
				.build();

		return new BaseResponse<>(
				modelMapper.map(categoryClipService.getCategoryClip(uuid, dto), CategoryClipGetInfoRespVO.class));
	}


}
