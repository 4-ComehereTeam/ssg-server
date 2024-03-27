package com.comehere.ssgserver.purchase.domain;

import java.time.LocalDateTime;

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
public class MemberPurchase extends BaseEntity {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(unique = true, nullable = false, updatable = false)
	private String purchaseCode;

	@Column(nullable = false, updatable = false)
	private Long memberId;

	@Column(nullable = false)
	private String requestMessage;

	@Column(nullable = false, updatable = false)
	private Long memberAddressId;

	@Builder
	public MemberPurchase(String purchaseCode, Long memberId, String requestMessage,
			Long memberAddressId) {
		this.purchaseCode = purchaseCode;
		this.memberId = memberId;
		this.requestMessage = requestMessage;
		this.memberAddressId = memberAddressId;
	}
}
