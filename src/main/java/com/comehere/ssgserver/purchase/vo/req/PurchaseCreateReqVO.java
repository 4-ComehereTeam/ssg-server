package com.comehere.ssgserver.purchase.vo.req;

import java.util.List;

import lombok.Getter;

@Getter
public class PurchaseCreateReqVO {
	private String name;

	private String phone;

	private String email;

	private String addressNickname;

	private String address;

	private String detailAddress;

	private String zipcode;

	private String requestMessage;

	private List<PurchaseListCreateReqVO> purchases;


}
