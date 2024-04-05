package com.comehere.ssgserver.option.dto.resp;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class SizeDTO {
	private Long optionId;

	private Long sizeId;

	private String value;

	private Integer stock;
}
