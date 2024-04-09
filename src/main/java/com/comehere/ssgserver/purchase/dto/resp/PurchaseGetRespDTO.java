package com.comehere.ssgserver.purchase.dto.resp;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class PurchaseGetRespDTO {
	private Long id;

	private String purchaseCode;

	@Builder
	public PurchaseGetRespDTO(Long id, String purchaseCode) {
		this.id = id;
		this.purchaseCode = purchaseCode;
	}
}
