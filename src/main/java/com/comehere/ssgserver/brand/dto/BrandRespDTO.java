package com.comehere.ssgserver.brand.dto;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class BrandRespDTO {
	private Long id;

	private String name;
}
