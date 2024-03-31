package com.comehere.ssgserver.brand.dto.resp;

import java.util.List;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class BrandItemListRespDTO {
	private Long brandId;

	private List<Long> items;

	private Boolean hasNext;
}
