package com.comehere.ssgserver.purchase.dto.resp;

import java.util.List;

import lombok.Builder;
import lombok.Getter;

@Getter
public class PurchasesGetRespDTO {
	private String purchaseCode;

	private List<Long> purchaseListIds;

	@Builder
	public PurchasesGetRespDTO(String purchaseCode, List<Long> purchaseListIds) {
		this.purchaseCode = purchaseCode;
		this.purchaseListIds = purchaseListIds;
	}
}
