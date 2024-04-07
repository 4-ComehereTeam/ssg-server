package com.comehere.ssgserver.bundle.vo.resp;

import java.util.List;

import lombok.Getter;

@Getter
public class BundleItemRespVO {
	private Long bundleId;

	private List<Long> items;
}
