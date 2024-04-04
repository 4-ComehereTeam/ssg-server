package com.comehere.ssgserver.purchase.domain;

public enum PurchaseListStatus {
	WAITING_FOR_PAYMENT("입금 대기중"),
	ACCEPTED("주문 접수"),
	CANCEL("주문 취소"),
	CANCEL_COMPLETED("주문 취소 완료"),
	PURCHASE_CONFIRMED("입금대"),
	REFUND_REQUESTED("구매 확정"),
	REFUND_COMPLETED("환불 요청"),
	RETURN_REQUESTED("반품 요청"),
	RETURN_COMPLETED("반품 완료");

	private final String status;

	PurchaseListStatus(String status) {
		this.status = status;
	}
}
