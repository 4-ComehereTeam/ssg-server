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
import lombok.Builder;
import lombok.NoArgsConstructor;

@Entity
@NoArgsConstructor
public class Address {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "member_id")
	private Member member;

	private String name;

	@Column(name = "phone", nullable = false)
	private String phone;

	@Column(name = "tel")
	private String tel;

	private String zipcode;

	private String address;

	private String detailAddress;

	@Column(columnDefinition = "Longtext", nullable = true)
	private String requestMessage;

	// 기본 배송지로 사용
	@Column(columnDefinition = "TINYINT", length = 1, nullable = true)
	private Short defaultAddress;

	@Builder
	public Address(Member member, String name, String phone, String tel, String zipcode, String address,
			String detailAddress, String requestMessage, Short defaultAddress) {
		this.member = member;
		this.name = name;
		this.phone = phone;
		this.tel = tel;
		this.zipcode = zipcode;
		this.address = address;
		this.detailAddress = detailAddress;
		this.requestMessage = requestMessage;
		this.defaultAddress = defaultAddress;
	}

}
