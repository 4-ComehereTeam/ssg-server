package com.comehere.ssgserver.option.dto.resp;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.ToString;

@Getter
@AllArgsConstructor
public class ColorDTO {
	private Long optionId;

	private Long colorId;

	private String value;

	private Integer stock;
}
