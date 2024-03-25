package com.comehere.ssgserver.purchase.domain;

import java.time.LocalDateTime;

import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;

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
@SQLDelete(sql = "UPDATE non_member_purchase SET deleted = true WHERE id = ?")
@Where(clause = "deleted = false")
public class NonMemberPurchase {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(nullable = false)
	private String code;

	@Column(nullable = false)
	private Long itemOptionId;

	@Column(nullable = false)
	private String itemName;

	@Column(nullable = false)
	private Long itemPrice;

	@Column(nullable = false)
	private Integer count;

	@Column(nullable = false)
	private String nonMemberName;

	@Column(nullable = false)
	private String phoneNumber;

	@Column(nullable = false)
	private String address;

	@Column(nullable = false)
	private String addressDetail;

	@Column(nullable = false)
	private String zipcode;

	@Column(nullable = false)
	private String email;

	@Column(nullable = false)
	private String requestMessage;

	@Column(nullable = false)
	private String cancellationReasons;

	private String detailReasons;

	@Column(nullable = false)
	private LocalDateTime purchaseDate;

	@Column(nullable = false, columnDefinition = "TINYINT")
	private Boolean deleted;

	@Builder
	public NonMemberPurchase(String code, Long itemOptionId, String itemName, Long itemPrice, Integer count,
			String nonMemberName, String phoneNumber, String address, String addressDetail, String zipcode,
			String email,
			LocalDateTime purchaseDate, Boolean deleted, String cancellationReasons, String requestMessage) {

		this.code = code;
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
		this.purchaseDate = purchaseDate;
		this.requestMessage = requestMessage;
		this.cancellationReasons = cancellationReasons;
		this.deleted = deleted;
	}

	public void deleteProcess(String cancellationReasons, String detailReasons) {
		this.cancellationReasons = cancellationReasons;
		this.detailReasons = detailReasons;
	}

	public void updateItemOptionId(Long itemOptionId) {
		this.itemOptionId = itemOptionId;
	}
}
