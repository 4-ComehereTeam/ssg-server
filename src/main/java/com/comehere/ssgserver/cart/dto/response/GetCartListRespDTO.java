package com.comehere.ssgserver.cart.dto.response;

import java.util.List;

import com.comehere.ssgserver.cart.dto.ItemCountDTO;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class GetCartListRespDTO {

	List<ItemCountDTO> itemOptions;
}
