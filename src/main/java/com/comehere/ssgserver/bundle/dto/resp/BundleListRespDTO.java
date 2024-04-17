package com.comehere.ssgserver.bundle.dto.resp;

import java.util.List;

import org.springframework.data.domain.Slice;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class BundleListRespDTO {
	private List<Long> bundles;

	private Integer currentPage;

	private Boolean hasNext;

	public static BundleListRespDTO toBuild(Slice<Long> slice) {
		return BundleListRespDTO.builder()
				.bundles(slice.getContent())
				.currentPage(slice.getNumber())
				.hasNext(slice.hasNext())
				.build();
	}
}
