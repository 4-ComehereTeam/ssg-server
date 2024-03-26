package com.comehere.ssgserver.purchase.dto;

import java.util.List;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class PurchaseIdDTO {
	private List<Long> purchaseIds;

	@Builder
	public PurchaseIdDTO(List<Long> purchaseIds) {
		this.purchaseIds = purchaseIds;
	}
}
