package com.comehere.ssgserver.clip.application;

import java.util.UUID;

import com.comehere.ssgserver.clip.dto.req.CateGoryClipStatusGetReqDTO;
import com.comehere.ssgserver.clip.dto.req.CategoriesClipDeleteReqDTO;
import com.comehere.ssgserver.clip.dto.req.CategoryClipCreateReqDTO;
import com.comehere.ssgserver.clip.dto.resp.CategoriesClipGetRespDTO;

public interface CategoryClipService {
	void createCategoryClip(UUID uuid, CategoryClipCreateReqDTO dto);

	void deleteCategoriesClip(UUID uuid, CategoriesClipDeleteReqDTO dto);

	CategoriesClipGetRespDTO getCategoriesClip(UUID uuid);

	Boolean getCategoryClipStatus(UUID uuid, CateGoryClipStatusGetReqDTO dto);
}
