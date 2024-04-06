package com.comehere.ssgserver.purchase.domain;

import lombok.Getter;

@Getter
public enum PurchaseStatus {
	ACCEPTED("주문 접수"),
	CANCEL("주문 삭제");

	private final String description;
	PurchaseStatus(String description) {
		this.description = description;
	}
}
