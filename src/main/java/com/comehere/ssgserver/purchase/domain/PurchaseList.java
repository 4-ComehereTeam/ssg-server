package com.comehere.ssgserver.purchase.domain;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

@Entity
public class PurchaseList {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "member_purchase_id")
	private MemberPurchase memberPurchase;

	private Long stockId;

	private String itemName;

	private Long itemPrice;

	@Column(columnDefinition = "TINYINT", length = 1, nullable = true)
	private Short isReview;

	private Integer count;

}
