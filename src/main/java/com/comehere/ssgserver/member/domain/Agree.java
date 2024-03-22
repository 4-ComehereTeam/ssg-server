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
	private Short marketingAgree;

	// 이메일 수신 동의
	@Column(columnDefinition = "TINYINT", length = 1, nullable = true)
	private Short emailAgree;

	// 약관동의
	@Column(columnDefinition = "TINYINT", length = 1, nullable = true)
	private Short termssAgree;

	// SMS 동의
	@Column(columnDefinition = "TINYINT", length = 1, nullable = true)
	private Short smsAgree;

	// 우편물 동의
	@Column(columnDefinition = "TINYINT", length = 1, nullable = true)
	private Short mailAgree;

	//텔레마케팅 동의
	@Column(columnDefinition = "TINYINT", length = 1, nullable = true)
	private Short callAgree;

	//간편로그인 동의
	@Column(columnDefinition = "TINYINT", length = 1, nullable = true)
	private Short simpleAgree;

	@Builder
	public Agree(String email, Short marketingAgree, Short termssAgree, Short smsAgree, Short mailAgree,
			Short callAgree, Short simpleAgree, Short emailAgree) {
		this.email = email;
		this.marketingAgree = marketingAgree;
		this.termssAgree = termssAgree;
		this.smsAgree = smsAgree;
		this.mailAgree = mailAgree;
		this.callAgree = callAgree;
		this.simpleAgree = simpleAgree;
		this.emailAgree = emailAgree;
	}
}
