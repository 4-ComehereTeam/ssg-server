package com.comehere.ssgserver.purchase.dto.resp;

import java.util.List;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class NonPurchaseGetRespDTO {
	private String purchaseCode;

	private List<Long> purchaseListIds;

	@Builder
	public NonPurchaseGetRespDTO(String purchaseCode, List<Long> purchaseListIds) {
		this.purchaseCode = purchaseCode;
		this.purchaseListIds = purchaseListIds;
	}
}
