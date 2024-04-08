package com.comehere.ssgserver.bundle.vo.req;

import java.time.LocalDate;
import java.util.List;

import lombok.Getter;

@Getter
public class CreateBundleReqVO {
	private String name;

	private List<Long> items;

	private LocalDate finishDate;

	private String image;

	private String alt;
}
