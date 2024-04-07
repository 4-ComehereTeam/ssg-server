package com.comehere.ssgserver.cart.dto.req;

import java.util.List;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class DeleteItemReqDTO {

	private List<Long> itemOptionIds;
}
