package com.comehere.ssgserver.purchase.domain;

import com.comehere.ssgserver.member.domain.Member;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

@Entity
public class Address {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "member_id")
	private Member member;

	private String name;

	private String phone;

	@Column(name = "tel", nullable = true)
	private String tel;

	private String zipcode;

	private String detail;

	@Column(columnDefinition = "Longtext", nullable = true)
	private String requestMessage;
	@Column(columnDefinition = "TINYINT", length = 1, nullable = true)
	private String isDefaultRequest;

	@Column(columnDefinition = "TINYINT", length = 1, nullable = true)
	private short isDefaultAddress;
}
