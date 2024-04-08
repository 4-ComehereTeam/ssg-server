package com.comehere.ssgserver.bundle.vo.resp;

import java.util.List;

import lombok.Getter;

@Getter
public class BundleListRespVO {
	private List<Long> bundles;

	private Long currentPage;

	private Boolean hasNext;
}
