package com.comehere.ssgserver.clip.infrastructure;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import com.comehere.ssgserver.clip.dto.req.CateGoryClipGetReqDTO;
import com.comehere.ssgserver.clip.dto.req.CategoryClipDeleteReqDTO;
import com.comehere.ssgserver.clip.dto.resp.CategoryClipGetRespDTO;

public interface CustomCategoryClipRepository {
	List<CategoryClipGetRespDTO> findCategoryClipGetRespDTOByUuid(UUID uuid);

	Boolean deleteByUuidAndBigCategoryIdAndMiddleCategoryIdAndSmallCategoryId(UUID uuid, CategoryClipDeleteReqDTO dto);

	Optional<Long> findIdByUuidAndBigCategoryIdAndMiddleCategoryIdAndSmallCategoryId(UUID uuid, CateGoryClipGetReqDTO dto);
}
