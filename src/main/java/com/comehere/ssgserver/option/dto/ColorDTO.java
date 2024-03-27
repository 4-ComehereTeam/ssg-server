package com.comehere.ssgserver.option.dto;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class ColorDTO {
	private Long optionId;

	private Long colorId;

	private String value;

	private Integer stock;
}
