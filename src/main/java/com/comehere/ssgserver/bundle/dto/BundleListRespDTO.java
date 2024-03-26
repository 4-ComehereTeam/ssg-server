package com.comehere.ssgserver.bundle.dto;

import java.util.List;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class BundleListRespDTO {
	private List<Long> bundles;
}
