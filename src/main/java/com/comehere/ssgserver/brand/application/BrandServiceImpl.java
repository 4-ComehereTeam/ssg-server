package com.comehere.ssgserver.brand.application;

import org.springframework.stereotype.Service;

import com.comehere.ssgserver.brand.domain.Brand;
import com.comehere.ssgserver.brand.dto.BrandRespDTO;
import com.comehere.ssgserver.brand.infrastructure.BrandWithItemRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class BrandServiceImpl implements BrandService {
	private final BrandWithItemRepository brandWithItemRepository;

	@Override
	public BrandRespDTO getBrand(Long itemId) {
		Brand brand = brandWithItemRepository.findByItemId(itemId).getBrand();

		return BrandRespDTO.builder()
				.id(brand.getId())
				.name(brand.getName())
				.build();
	}
}
