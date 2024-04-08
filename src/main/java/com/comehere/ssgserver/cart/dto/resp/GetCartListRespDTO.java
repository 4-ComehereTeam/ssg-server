package com.comehere.ssgserver.cart.dto.resp;

import java.util.List;

import com.comehere.ssgserver.cart.dto.ItemCountDTO;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class GetCartListRespDTO {

	private List<ItemCountDTO> itemOptions;
}
