package com.comehere.ssgserver.option.dto;

import java.util.List;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class SizeRespDTO {
	private Long itemId;

	private Long colorId;

	private List<SizeDTO> sizes;
}
