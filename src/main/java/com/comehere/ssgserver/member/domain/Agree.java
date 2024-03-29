package com.comehere.ssgserver.member.domain;

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
public class Agree {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	//uuid로 수정
	private String email;

	//ssgPoint 동의 여부
	@Column(nullable = false)
	private Boolean ssgPointMktAgr1;

	@Column(nullable = false)
	private Boolean getSsgPointMktAgr2;

	@Column(nullable = false)
	private Boolean ssgPointEmail;

	@Column(nullable = false)
	private Boolean ssgPointSms;

	@Column(nullable = false)
	private Boolean ssgPointMail;

	@Column(nullable = false)
	private Boolean ssgPointCall;

	@Column(nullable = false)
	//ssgcom 동의 여부
	private Boolean ssgcomMktAgr1;

	@Column(nullable = false)
	private Boolean ssgcomEmail;

	@Column(nullable = false)
	private Boolean ssgcomSms;

	@Builder
	public Agree(String email, Boolean ssgPointMktAgr1, Boolean ssgPointMktAgr2, Boolean ssgPointEmail,
			Boolean ssgPointSms, Boolean ssgPointMail, Boolean ssgPointCall, Boolean ssgcomMktAgr1, Boolean ssgcomEmail,
			Boolean ssgcomSms) {
		this.email = email;
		this.ssgPointMktAgr1 = ssgPointMktAgr1;
		this.getSsgPointMktAgr2 = ssgPointMktAgr2;
		this.ssgPointEmail = ssgPointEmail;
		this.ssgPointSms = ssgPointSms;
		this.ssgPointMail = ssgPointMail;
		this.ssgPointCall = ssgPointCall;
		this.ssgcomMktAgr1 = ssgcomMktAgr1;
		this.ssgcomEmail = ssgcomEmail;
		this.ssgcomSms = ssgcomSms;
	}

}
