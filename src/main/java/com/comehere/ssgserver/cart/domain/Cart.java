package com.comehere.ssgserver.cart.domain;

import java.util.UUID;

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
public class Cart {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	private Long itemOptionId;

	private Integer itemCount;

	// 장바구니에 담긴 상품을 선택했는지 여부 -> 선택상태 재 로그인 시에도 유지
	private Boolean itemCheck;

	private Long memberAddressId;

	// 주문 후에도 계속 담아두기의 기능에 사용될 필드
	private Boolean pinStatus;

	private UUID uuid;

	@Builder
	public Cart(Long id, UUID uuid, Long itemOptionId, Integer itemCount, Boolean itemCheck, Long memberAddressId,
			Boolean pinStatus) {
		this.id = id;
		this.uuid = uuid;
		this.itemOptionId = itemOptionId;
		this.itemCount = itemCount;
		this.itemCheck = itemCheck;
		this.memberAddressId = memberAddressId;
		this.pinStatus = pinStatus;
	}
}

