package com.comehere.ssgserver.option.dto.resp;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class OptionRespDTO {
	private Long itemId;

	private Boolean hasColor;

	private Boolean hasSize;

	private Boolean hasEtc;
}
