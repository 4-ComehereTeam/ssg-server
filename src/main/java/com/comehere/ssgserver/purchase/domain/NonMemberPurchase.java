package com.comehere.ssgserver.purchase.domain;

import org.hibernate.annotations.DynamicInsert;
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
@DynamicInsert
@SQLDelete(sql = "UPDATE non_member_purchase SET deleted = true WHERE id = ?")
@Where(clause = "deleted = false")
public class NonMemberPurchase extends BaseEntity {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(nullable = false, updatable = false)
	private String purchaseCode;

	@Column(nullable = false)
	private Long itemOptionId;

	@Column(nullable = false, updatable = false)
	private String itemName;

	@Column(nullable = false, updatable = false)
	private Long itemPrice;

	@Column(nullable = false, updatable = false)
	private Integer count;

	@Column(nullable = false, updatable = false)
	private String nonMemberName;

	@Column(nullable = false, updatable = false)
	private String phoneNumber;

	@Column(nullable = false, updatable = false)
	private String address;

	@Column(nullable = false, updatable = false)
	private String addressDetail;

	@Column(nullable = false, updatable = false)
	private String zipcode;

	@Column(nullable = false, updatable = false)
	private String email;

	@Column(nullable = false, updatable = false)
	private String requestMessage;

	@Column(nullable = false)
	private String cancellationReasons;

	private String detailReasons;

	@Column(nullable = false)
	private Boolean deleted;

	@Builder
	public NonMemberPurchase(Long id, String purchaseCode, Long itemOptionId, String itemName, Long itemPrice,
			Integer count, String nonMemberName, String phoneNumber, String address, String addressDetail,
			String zipcode,
			String email, String requestMessage, String cancellationReasons, String detailReasons, Boolean deleted) {
		this.id = id;
		this.purchaseCode = purchaseCode;
		this.itemOptionId = itemOptionId;
		this.itemName = itemName;
		this.itemPrice = itemPrice;
		this.count = count;
		this.nonMemberName = nonMemberName;
		this.phoneNumber = phoneNumber;
		this.address = address;
		this.addressDetail = addressDetail;
		this.zipcode = zipcode;
		this.email = email;
		this.requestMessage = requestMessage;
		this.cancellationReasons = cancellationReasons;
		this.detailReasons = detailReasons;
		this.deleted = deleted;
	}
}

