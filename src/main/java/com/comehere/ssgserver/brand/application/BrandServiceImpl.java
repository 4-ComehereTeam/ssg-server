package com.comehere.ssgserver.brand.application;

import org.springframework.stereotype.Service;

import com.comehere.ssgserver.brand.domain.Brand;
import com.comehere.ssgserver.brand.domain.BrandWithItem;
import com.comehere.ssgserver.brand.dto.req.BrandReqDTO;
import com.comehere.ssgserver.brand.dto.resp.BrandInfoRespDTO;
import com.comehere.ssgserver.brand.infrastructure.BrandRepository;
import com.comehere.ssgserver.brand.infrastructure.BrandWithItemRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class BrandServiceImpl implements BrandService {
	private final BrandWithItemRepository brandWithItemRepository;
	private final BrandRepository brandRepository;

	@Override
	public BrandInfoRespDTO getBrand(Long itemId) {
		BrandWithItem brandWithItem = brandWithItemRepository.findByItemId(itemId)
				.orElseThrow(() -> new IllegalStateException("브랜드 정보가 없는 상품입니다."));

		return BrandInfoRespDTO.toBuild(brandWithItem.getBrand());
	}

	@Override
	public void createBrand(BrandReqDTO dto) {
		brandRepository.save(Brand.builder()
						.name(dto.getName())
				.build());
	}
}
