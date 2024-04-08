package com.comehere.ssgserver.bundle.dto.resp;

import com.comehere.ssgserver.bundle.domain.Bundle;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class BundleInfoRespDTO {
	private Long bundleId;

	private String name;

	private Long minPrice;

	private String imageUrl;

	private String alt;

	public static BundleInfoRespDTO toBuild(Bundle bundle) {
		return BundleInfoRespDTO.builder()
				.bundleId(bundle.getId())
				.name(bundle.getName())
				.alt(bundle.getAlt())
				.minPrice(bundle.getMinPrice())
				.imageUrl(bundle.getImageUrl())
				.build();
	}
}
