package com.comehere.ssgserver.member.vo.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class SsgPointAgreesVO {

	private Boolean ssgPointMktAgr1;

	private Boolean ssgPointMktAgr2;

	private Boolean ssgPointEmail;

	private Boolean ssgPointSms;

	private Boolean ssgPointMail;

	private Boolean ssgPointCall;
}
