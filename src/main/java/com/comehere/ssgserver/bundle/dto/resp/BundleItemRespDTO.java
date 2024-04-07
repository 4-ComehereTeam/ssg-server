package com.comehere.ssgserver.bundle.dto.resp;

import java.util.List;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class BundleItemRespDTO {
	private Long bundleId;

	private List<Long> items;
}
