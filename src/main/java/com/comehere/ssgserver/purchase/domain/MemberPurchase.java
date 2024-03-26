package com.comehere.ssgserver.purchase.domain;

import java.time.LocalDateTime;

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
public class MemberPurchase {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(unique = true)
	private String purchaseCode;

	private Long memberId;

	private LocalDateTime purchaseDate;

	private String requestMessage;

	private Long memberAddressId;

	@Builder
	public MemberPurchase(String purchaseCode, Long memberId, LocalDateTime purchaseDate, String requestMessage,
			Long memberAddressId) {
		this.purchaseCode = purchaseCode;
		this.memberId = memberId;
		this.purchaseDate = purchaseDate;
		this.requestMessage = requestMessage;
		this.memberAddressId = memberAddressId;
	}
}
