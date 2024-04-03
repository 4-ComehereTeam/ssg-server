package com.comehere.ssgserver.bundle.dto.req;

import java.time.LocalDate;
import java.util.List;

import lombok.Getter;

@Getter
public class CreateBundleReqDTO {
	private String name;

	private Long brandId;

	private List<Long> items;

	private LocalDate finishDate;
}
