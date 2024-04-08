package com.comehere.ssgserver.purchase.dto.req;

import java.util.List;

import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class PurchaseCreateReqDTO {
	private String senderName;

	private String senderPhone;

	private String senderEmail;

	private String recipientName;

	private String recipientPhone;

	private String recipientEmail;

	private String addressNickname;

	private String address;

	private String detailAddress;

	private String zipcode;

	private String requestMessage;

	private List<PurchaseListCreateReqDTO> purchases;
}
