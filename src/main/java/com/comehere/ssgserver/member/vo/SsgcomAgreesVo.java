package com.comehere.ssgserver.member.vo;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class SsgcomAgreesVo {

	private Boolean ssgcomMktAgr1;

	private Boolean ssgcomEmail;

	private Boolean ssgcomSms;
}
