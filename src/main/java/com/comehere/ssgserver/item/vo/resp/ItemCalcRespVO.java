package com.comehere.ssgserver.item.vo.resp;

import com.comehere.ssgserver.item.dto.resp.ItemCalcRespDTO;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class ItemCalcRespVO {
	private Long reviewCount;

	private Double averageStar;
}
