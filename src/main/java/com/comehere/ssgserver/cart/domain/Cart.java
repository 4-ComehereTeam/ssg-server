package com.comehere.ssgserver.cart.domain;

import com.comehere.ssgserver.member.domain.Member;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

@Entity
public class Cart {

	@Id
	@GeneratedValue(strategy = jakarta.persistence.GenerationType.IDENTITY)
	private Long id;

	@ManyToOne(fetch = jakarta.persistence.FetchType.LAZY)
	@JoinColumn(name = "member_id")
	private Member member;

	private Long stockId;

	private int itemCount;

	@Column(columnDefinition = "TINYINT", length = 1, nullable = true)
	private short itemCheck;

	private Long memberAddressId;

	@Column(columnDefinition = "TINYINT", length = 1, nullable = true)
	private short pinStatus;
}

