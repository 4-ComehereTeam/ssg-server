package com.comehere.ssgserver.option.dto.resp;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class SizeDTO {
	private Long optionId;

	private Long sizeId;

	private String value;

	private Integer stock;
}
