package com.comehere.ssgserver.purchase.domain;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class NonMemberPurchase {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	private String code;

	private Long stockId;

	private String itemName;

	private Long itemPrice;

	private Integer count;

	private String phoneNumber;

	private String address;

	private String zipcode;

	private String email;
}
