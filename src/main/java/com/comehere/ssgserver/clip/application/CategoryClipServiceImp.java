package com.comehere.ssgserver.clip.application;

import java.util.UUID;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.comehere.ssgserver.clip.domain.CategoryClip;
import com.comehere.ssgserver.clip.dto.req.CateGoryClipStatusGetReqDTO;
import com.comehere.ssgserver.clip.dto.req.CategoriesClipDeleteReqDTO;
import com.comehere.ssgserver.clip.dto.req.CategoryClipCreateReqDTO;
import com.comehere.ssgserver.clip.dto.resp.CategoriesClipGetRespDTO;
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
		dto.getCategoryClipIds()
				.forEach(categoryClipDeleteReqDTO -> {
					categoryClipRepository.deleteByUuidAndBigCategoryIdAndMiddleCategoryIdAndSmallCategoryId(uuid,
							categoryClipDeleteReqDTO);
				});
	}

	@Override
	public CategoriesClipGetRespDTO getCategoriesClip(UUID uuid) {
		return CategoriesClipGetRespDTO.builder()
				.categoryClip(categoryClipRepository.findCategoryClipGetRespDTOByUuid(uuid))
				.build();
	}

	@Override
	public Boolean getCategoryClipStatus(UUID uuid, CateGoryClipStatusGetReqDTO dto) {
		if (categoryClipRepository.existsByUuidAndBigCategoryIdAndMiddleCategoryIdAndSmallCategoryId(uuid,
				dto.getBigCategoryId(),
				dto.getMiddleCategoryId(),
				dto.getSmallCategoryId())) {
			return true;
		}

		throw new BaseException(BaseResponseStatus.CLIP_CATEGORY_NOT_FOUND);
	}
}
