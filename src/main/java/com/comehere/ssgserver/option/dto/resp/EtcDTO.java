package com.comehere.ssgserver.option.dto.resp;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class EtcDTO {
	private Long optionId;

	private Long etcId;

	private String value;

	private Integer stock;
}
