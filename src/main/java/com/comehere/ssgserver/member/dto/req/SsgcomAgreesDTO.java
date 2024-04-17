package com.comehere.ssgserver.member.dto.req;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class SsgcomAgreesDTO {

	private Boolean ssgcomMktAgr1;

	private Boolean ssgcomEmail;

	private Boolean ssgcomSms;
}
