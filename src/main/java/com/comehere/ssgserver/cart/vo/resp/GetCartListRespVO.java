package com.comehere.ssgserver.cart.vo.resp;

import java.util.List;

import com.comehere.ssgserver.cart.dto.ItemCountDTO;

import lombok.Getter;

@Getter
public class GetCartListRespVO {

	private List<ItemCountDTO> itemOptions;
}
