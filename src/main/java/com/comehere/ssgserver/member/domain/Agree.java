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

	//ssgPoint 동의 여부
	@Column(columnDefinition = "TINYINT", length = 1)
	private Boolean ssgPointMktAgr1;

	@Column(columnDefinition = "TINYINT", length = 1)
	private Boolean getSsgPointMktAgr2;

	@Column(columnDefinition = "TINYINT", length = 1)
	private Boolean ssgPointEmail;

	@Column(columnDefinition = "TINYINT", length = 1)
	private Boolean ssgPointSms;

	@Column(columnDefinition = "TINYINT", length = 1)
	private Boolean ssgPointMail;

	@Column(columnDefinition = "TINYINT", length = 1)
	private Boolean ssgPointCall;

	//ssgcom 동의 여부
	@Column(columnDefinition = "TINYINT", length = 1)
	private Boolean ssgcomMktAgr1;

	@Column(columnDefinition = "TINYINT", length = 1)
	private Boolean ssgcomEmail;

	@Column(columnDefinition = "TINYINT", length = 1)
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
