package com.comehere.ssgserver.option.dto.resp;

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
