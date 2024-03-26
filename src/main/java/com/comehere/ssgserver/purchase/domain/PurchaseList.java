package com.comehere.ssgserver.purchase.domain;

import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@NoArgsConstructor
@Getter
@SQLDelete(sql = "UPDATE purchase_list SET deleted = true WHERE id = ?")
@Where(clause = "deleted = false")
public class PurchaseList {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "member_purchase_id")
	private MemberPurchase memberPurchase;

	@Column(nullable = false)
	private Long itemOptionId;

	@Column(nullable = false)
	private String itemName;

	@Column(nullable = false)
	private Long itemPrice;

	@Column(nullable = false)
	private Integer count;

	@Column(nullable = false)
	private Boolean reviewWrite;

	@Column(nullable = false)
	private String cancellationReasons;

	private String detailReasons;

	@Column(nullable = false)
	private Boolean deleted;

	@Builder
	public PurchaseList(MemberPurchase memberPurchase, Long itemOptionId, String itemName, Long itemPrice,
			Integer count, String cancellationReasons, String detailReasons,
			Boolean reviewWrite, Boolean deleted) {
		this.memberPurchase = memberPurchase;
		this.itemOptionId = itemOptionId;
		this.itemName = itemName;
		this.itemPrice = itemPrice;
		this.count = count;
		this.cancellationReasons = cancellationReasons;
		this.detailReasons = detailReasons;
		this.reviewWrite = reviewWrite;
		this.deleted = deleted;
	}

}
