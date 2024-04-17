package com.comehere.ssgserver.option.dto.resp;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Builder
public class HasOptionRespDTO {
	private Long itemId;

	private Boolean hasColor;

	private Boolean hasSize;

	private Boolean hasEtc;
}
