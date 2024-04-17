package com.comehere.ssgserver.purchase.dto.resp;

import lombok.Builder;
import lombok.Getter;

@Getter
public class DefaultCheckRespDTO {

	private Long addressId;

	@Builder
	public DefaultCheckRespDTO(Long addressId) {
		this.addressId = addressId;
	}
}
