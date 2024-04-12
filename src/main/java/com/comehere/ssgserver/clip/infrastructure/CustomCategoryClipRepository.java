package com.comehere.ssgserver.clip.infrastructure;

import java.util.List;
import java.util.UUID;

import com.comehere.ssgserver.clip.dto.req.CategoryClipDeleteReqDTO;
import com.comehere.ssgserver.clip.dto.resp.CategoryClipGetRespDTO;

public interface CustomCategoryClipRepository {
	List<CategoryClipGetRespDTO> findCategoryClipGetRespDTOByUuid(UUID uuid);

	void deleteByUuidAndBigCategoryIdAndMiddleCategoryIdAndSmallCategoryId(UUID uuid, CategoryClipDeleteReqDTO dto);
}
