package com.comehere.ssgserver.brand.vo.resp;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class BrandInfoRespVO {
	private Long id;

	private String name;
}
