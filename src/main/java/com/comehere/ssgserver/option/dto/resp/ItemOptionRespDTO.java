package com.comehere.ssgserver.option.dto.resp;

import java.util.List;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class ItemOptionRespDTO {
	private Long itemId;
	private List<OptionDTO> options;
}
