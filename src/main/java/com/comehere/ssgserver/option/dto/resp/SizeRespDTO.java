package com.comehere.ssgserver.option.dto.resp;

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
