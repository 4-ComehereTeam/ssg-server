package com.comehere.ssgserver.member.domain;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import lombok.Builder;
import lombok.NoArgsConstructor;

@Entity
@NoArgsConstructor
public class Agree {

	@Id
	@GeneratedValue(strategy = jakarta.persistence.GenerationType.IDENTITY)
	private Long id;

	private String email;

	// 마케팅 동의
	@Column(columnDefinition = "TINYINT", length = 1, nullable = true)
	private Boolean marketingAgree;

	// 이메일 수신 동의
	@Column(columnDefinition = "TINYINT", length = 1, nullable = true)
	private Boolean emailAgree;

	// SMS 동의
	@Column(columnDefinition = "TINYINT", length = 1, nullable = true)
	private Boolean smsAgree;

	// 우편물 동의
	@Column(columnDefinition = "TINYINT", length = 1, nullable = true)
	private Boolean mailAgree;

	//텔레마케팅 동의
	@Column(columnDefinition = "TINYINT", length = 1, nullable = true)
	private Boolean callAgree;

	//간편로그인 동의
	@Column(columnDefinition = "TINYINT", length = 1, nullable = true)
	private Boolean simpleAgree;

	@Builder
	public Agree(String email, Boolean marketingAgree, Boolean smsAgree, Boolean mailAgree, Boolean callAgree,
			Boolean simpleAgree, Boolean emailAgree) {
		this.email = email;
		this.marketingAgree = marketingAgree;
		this.smsAgree = smsAgree;
		this.mailAgree = mailAgree;
		this.callAgree = callAgree;
		this.simpleAgree = simpleAgree;
		this.emailAgree = emailAgree;
	}
}
