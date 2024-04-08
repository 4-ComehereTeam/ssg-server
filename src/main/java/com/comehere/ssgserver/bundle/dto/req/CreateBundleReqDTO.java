package com.comehere.ssgserver.bundle.dto.req;

import java.time.LocalDate;
import java.util.List;

import lombok.Getter;

@Getter
public class CreateBundleReqDTO {
	private String name;

	private List<Long> items;

	private LocalDate finishDate;

	private String image;

	private String alt;
}
