package com.comehere.ssgserver.item.dto.resp;

import java.util.List;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class BestItemRespDTO {
	private List<Long> items;
}
