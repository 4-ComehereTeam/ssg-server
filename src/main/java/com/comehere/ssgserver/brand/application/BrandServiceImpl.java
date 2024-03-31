package com.comehere.ssgserver.brand.application;

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
import org.springframework.stereotype.Service;

import com.comehere.ssgserver.brand.domain.Brand;
import com.comehere.ssgserver.brand.domain.BrandWithItem;
import com.comehere.ssgserver.brand.dto.req.BrandReqDTO;
import com.comehere.ssgserver.brand.dto.resp.BrandInfoRespDTO;
import com.comehere.ssgserver.brand.dto.resp.BrandItemListRespDTO;
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

	@Override
	public BrandItemListRespDTO brandItemList(Long brandId, Pageable page) {
		Slice<Long> result = brandWithItemRepository.findByBrandId(brandId, page);

		if(result.isEmpty()) {
			throw new IllegalStateException("존재하지 않는 브랜드입니다.");
		}

		return BrandItemListRespDTO.builder()
				.brandId(brandId)
				.items(result.getContent())
				.hasNext(result.hasNext())
				.build();
	}

}
