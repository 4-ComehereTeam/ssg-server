package com.comehere.ssgserver.bundle.vo;

import java.util.List;

import lombok.Getter;

@Getter
public class BundleReqVO {
	private String name;

	private List<Long> items;
}
