package com.comehere.ssgserver.brand.vo.resp;

import java.util.List;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class BrandItemListRespVO {
	private Long brandId;

	private List<Long> items;

	private Boolean hasNext;
}
