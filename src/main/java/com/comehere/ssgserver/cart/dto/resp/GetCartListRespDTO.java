package com.comehere.ssgserver.cart.dto.resp;

import java.util.List;

import com.comehere.ssgserver.cart.dto.ItemCountDTO;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class GetCartListRespDTO {

	private List<ItemCountDTO> itemOptions;
}
