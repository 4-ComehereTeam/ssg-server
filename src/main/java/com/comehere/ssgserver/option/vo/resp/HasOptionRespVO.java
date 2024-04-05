package com.comehere.ssgserver.option.vo.resp;

import lombok.Builder;
import lombok.Getter;

@Getter
public class HasOptionRespVO {
	private Long itemId;

	private Boolean hasColor;

	private Boolean hasSize;

	private Boolean hasEtc;
}
