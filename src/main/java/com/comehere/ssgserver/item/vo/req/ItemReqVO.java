package com.comehere.ssgserver.item.vo.req;

import java.util.List;

import javax.swing.text.html.Option;

import com.comehere.ssgserver.option.domain.ItemOption;

import lombok.Getter;

@Getter
public class ItemReqVO {
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
