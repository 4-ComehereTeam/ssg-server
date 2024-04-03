package com.comehere.ssgserver.bundle.dto.resp;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class BundleRespDTO {
	private Long bundleId;

	private String name;

	private Long minPrice;
}
