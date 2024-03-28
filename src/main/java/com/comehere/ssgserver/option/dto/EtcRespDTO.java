package com.comehere.ssgserver.option.dto;

import java.util.List;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class EtcRespDTO {
	private Long itemId;

	private Long colorId;

	private Long sizeId;

	private List<EtcDTO> etcs;
}
