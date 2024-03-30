package com.comehere.ssgserver.item.vo.resp;

import com.comehere.ssgserver.item.dto.resp.ItemDetailRespDTO;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class ItemDetailRespVO {
	private String itemName;

	private String itemCode;

	private Long price;

	private Integer discountRate;
}
