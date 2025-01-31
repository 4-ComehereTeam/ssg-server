package com.comehere.ssgserver.clip.application;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.comehere.ssgserver.clip.domain.CategoryClip;
import com.comehere.ssgserver.clip.dto.req.CateGoryClipGetReqDTO;
import com.comehere.ssgserver.clip.dto.req.CategoriesClipDeleteReqDTO;
import com.comehere.ssgserver.clip.dto.req.CategoryClipCreateReqDTO;
import com.comehere.ssgserver.clip.dto.resp.CategoriesClipGetRespDTO;
import com.comehere.ssgserver.clip.dto.resp.CategoryClipGetInfoRespDTO;
import com.comehere.ssgserver.clip.dto.resp.CategoryClipGetRespDTO;
import com.comehere.ssgserver.clip.infrastructure.CategoryClipRepository;
import com.comehere.ssgserver.common.exception.BaseException;
import com.comehere.ssgserver.common.response.BaseResponseStatus;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class CategoryClipServiceImp implements CategoryClipService {
	private final CategoryClipRepository categoryClipRepository;

	@Override
	public void createCategoryClip(UUID uuid, CategoryClipCreateReqDTO dto) {
		if (categoryClipRepository.existsByUuidAndBigCategoryIdAndMiddleCategoryIdAndSmallCategoryId(uuid,
				dto.getBigCategoryId(), dto.getMiddleCategoryId(), dto.getSmallCategoryId())) {
			throw new BaseException(BaseResponseStatus.CLIP_CATEGORY_ALREADY_ADDED);
		}

		categoryClipRepository.save(CategoryClip.builder()
				.uuid(uuid)
				.bigCategoryId(dto.getBigCategoryId())
				.middleCategoryId(dto.getMiddleCategoryId())
				.smallCategoryId(dto.getSmallCategoryId())
				.build());
	}

	@Override
	@Transactional
	public void deleteCategoriesClip(UUID uuid, CategoriesClipDeleteReqDTO dto) {
		List<Boolean> deleteResult = new ArrayList<>();

		dto.getCategoryClipIds()
				.forEach(categoryClipDeleteReqDTO -> {
					deleteResult.add(categoryClipRepository.deleteByUuidAndBigCategoryIdAndMiddleCategoryIdAndSmallCategoryId(uuid,
							categoryClipDeleteReqDTO));
				});

		if (deleteResult.contains(false)) {
			throw new BaseException(BaseResponseStatus.CLIP_CATEGORY_NOT_DELETED);
		}
	}

	@Override
	public CategoriesClipGetRespDTO getCategoriesClip(UUID uuid) {
		return CategoriesClipGetRespDTO.builder()
				.categoryClip(categoryClipRepository.findCategoryClipGetRespDTOByUuid(uuid))
				.build();
	}

	@Override
	public Boolean getCategoryClipStatus(UUID uuid, CateGoryClipGetReqDTO dto) {
		if (categoryClipRepository.existsByUuidAndBigCategoryIdAndMiddleCategoryIdAndSmallCategoryId(uuid,
				dto.getBigCategoryId(),
				dto.getMiddleCategoryId(),
				dto.getSmallCategoryId())) {
			return true;
		}

		throw new BaseException(BaseResponseStatus.CLIP_CATEGORY_NOT_FOUND);
	}

	@Override
	public CategoryClipGetInfoRespDTO getCategoryClip(UUID uuid, CateGoryClipGetReqDTO dto) {
		Long categoryClipId = categoryClipRepository.findIdByUuidAndBigCategoryIdAndMiddleCategoryIdAndSmallCategoryId(
				uuid, dto).orElseThrow(() -> new BaseException(BaseResponseStatus.CLIP_CATEGORY_NOT_FOUND));

		return CategoryClipGetInfoRespDTO.builder()
				.id(categoryClipId)
				.isCliped(true)
				.build();
	}
}
