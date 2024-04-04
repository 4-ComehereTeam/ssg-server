package com.comehere.ssgserver.purchase.domain;

public enum PurchaseStatus {
	ACCEPTED("주문 접수"),
	CANCEL("주문 삭제");

	private final String status;
	PurchaseStatus(String status) {
		this.status = status;
	}
}
