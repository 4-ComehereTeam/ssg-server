package com.comehere.ssgserver.member.dto.req;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class JoinReqDTO {

	private String signinId;

	private String password;

	private String name;

	private String phone;

	private String email;

	private Short gender;

	private AddressInfoReqDTO addressInfoReqDTO;

	private SsgcomAgreesDTO ssgcomAgreesDTO;

	private SsgPointAgreesDTO ssgPointAgreesDTO;
}
