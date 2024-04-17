package com.comehere.ssgserver.purchase.dto.req;

import lombok.Builder;
import lombok.Getter;

@Getter
public class PurchaseGetReqDTO {
	private String startDate;
	private String endDate;
	private Boolean acceptedStatus;

	@Builder
	public PurchaseGetReqDTO(String startDate, String endDate, Boolean acceptedStatus) {
		this.startDate = startDate;
		this.endDate = endDate;
		this.acceptedStatus = acceptedStatus;
	}
}
