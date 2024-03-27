package com.comehere.ssgserver.purchase.domain;

import com.comehere.ssgserver.common.entity.BaseEntity;
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
public class Address extends BaseEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "member_id")
	private Member member;

	// 받는 사람 이름
	private String name;

	// 배송지 별칭
	private String nickname;

	// 받는 사람 휴대폰번호
	@Column(nullable = false)
	private String phone;

	// 받는 사람 전화번호
	private String tel;

	private String zipcode;

	private String address;

	private String detailAddress;

	@Column(columnDefinition = "Longtext", nullable = true)
	private String requestMessage;

	// 기본 배송지로 사용
	@Column(nullable = true)
	private Boolean defaultAddress;

	@Builder
	public Address(Member member, String name, String nickname, String phone, String tel, String zipcode,
			String address, String detailAddress, String requestMessage, Boolean defaultAddress) {
		this.member = member;
		this.name = name;
		this.nickname = nickname;
		this.phone = phone;
		this.tel = tel;
		this.zipcode = zipcode;
		this.address = address;
		this.detailAddress = detailAddress;
		this.requestMessage = requestMessage;
		this.defaultAddress = defaultAddress;
	}
}
