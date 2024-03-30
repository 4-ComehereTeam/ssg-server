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

	private String bigCategory;

	private String middleCategory;

	private String smallCategory;

	private String detailCategory;

	private String brand;

	private List<ItemOption> options;
}
