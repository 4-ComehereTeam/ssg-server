package com.comehere.ssgserver.purchase.domain;

import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;

import com.comehere.ssgserver.common.entity.BaseEntity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@NoArgsConstructor
@SQLDelete(sql = "UPDATE purchase_list SET deleted = true WHERE id = ?")
@Where(clause = "deleted = false")
public class PurchaseList extends BaseEntity {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(nullable = false, updatable = false)
	private Long purchaseId;

	@Column(nullable = false, updatable = false)
	private Long itemOptionId;

	@Column(nullable = false, updatable = false)
	private String itemName;

	@Column(nullable = false, updatable = false)
	private Long itemPrice;

	@Column(updatable = false)
	private Integer itemDiscountRate;

	@Column(nullable = false)
	private Integer count;

	@Column(nullable = false)
	private String cancelReason;

	@Column(nullable = false)
	private String detailReason;

	@Column(nullable = false)
	private Boolean wroteReview;

	@Column(nullable = false)
	private Boolean deleted;

	@Builder

	public PurchaseList(Long id, Long purchaseId, Long itemOptionId, String itemName, Long itemPrice,
			Integer itemDiscountRate, Integer count, String cancelReason, String detailReason, Boolean wroteReview,
			Boolean deleted) {
		this.id = id;
		this.purchaseId = purchaseId;
		this.itemOptionId = itemOptionId;
		this.itemName = itemName;
		this.itemPrice = itemPrice;
		this.itemDiscountRate = itemDiscountRate;
		this.count = count;
		this.cancelReason = cancelReason;
		this.detailReason = detailReason;
		this.wroteReview = wroteReview;
		this.deleted = deleted;
	}
}
