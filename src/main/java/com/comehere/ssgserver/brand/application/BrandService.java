package com.comehere.ssgserver.brand.application;

import com.comehere.ssgserver.brand.dto.req.BrandReqDTO;
import com.comehere.ssgserver.brand.dto.resp.BrandInfoRespDTO;

public interface BrandService {
	BrandInfoRespDTO getBrand(Long itemId);

	void createBrand(BrandReqDTO dto);
}
