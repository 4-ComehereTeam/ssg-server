package com.comehere.ssgserver.brand.dto.resp;

import com.comehere.ssgserver.brand.domain.Brand;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class BrandInfoRespDTO {
	private Long id;

	private String name;

	public static BrandInfoRespDTO toBuild(Brand brand) {
		return BrandInfoRespDTO.builder()
				.id(brand.getId())
				.name(brand.getName())
				.build();
	}
}
