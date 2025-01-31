package com.comehere.ssgserver.cart.dto.req;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class DeleteItemReqDTO {

	private List<Long> itemOptionIds;
}
