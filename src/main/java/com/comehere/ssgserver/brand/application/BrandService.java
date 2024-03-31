package com.comehere.ssgserver.brand.application;

import java.util.List;

import org.springframework.data.domain.Pageable;

import com.comehere.ssgserver.brand.dto.req.BrandReqDTO;
import com.comehere.ssgserver.brand.dto.resp.BrandInfoRespDTO;
import com.comehere.ssgserver.brand.dto.resp.BrandItemListRespDTO;

public interface BrandService {
	BrandInfoRespDTO getBrand(Long itemId);

	void createBrand(BrandReqDTO dto);

	BrandItemListRespDTO brandItemList(Long brandId, Pageable page);
}
