package com.comehere.ssgserver.item.dto.req;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class ItemReqDTO {
	private String name;

	private Long price;

	private Integer discountRate;

	private String description;

	private Integer bigCategory;

	private Integer middleCategory;

	private Integer smallCategory;

	private Integer detailCategory;

	private Long brand;
}
