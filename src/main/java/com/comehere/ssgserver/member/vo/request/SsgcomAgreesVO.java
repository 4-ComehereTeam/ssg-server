package com.comehere.ssgserver.member.vo.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class SsgcomAgreesVO {

	private Boolean ssgcomMktAgr1;

	private Boolean ssgcomEmail;

	private Boolean ssgcomSms;
}
