package com.comehere.ssgserver.member.vo;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Getter
@ToString
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class SignUpRequestVo {

	private String signinId;
	private String password;
	private String name;
	private String phone;
	private String email;

	private AddressInfoVo addressInfo;
	private AgreeVo agree;

}
