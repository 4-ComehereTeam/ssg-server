package com.comehere.ssgserver.option.dto;

import java.util.List;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class OptionRespDTO {
	private Long itemId;

	private Boolean hasColor;

	private Boolean hasSize;

	private Boolean hasEtc;
}
