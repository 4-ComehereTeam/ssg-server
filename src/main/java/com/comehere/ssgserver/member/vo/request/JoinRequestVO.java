package com.comehere.ssgserver.member.vo.request;

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
public class JoinRequestVO {

	private String signinId;

	private String password;

	private String name;

	private String phone;

	private String email;

	// 0 : 남/ 1 : 여
	private Short gender;

	private AddressInfoVO addressInfoVo;

	private SsgcomAgreesVO ssgcomAgreesVo;

	private SsgPointAgreesVO ssgPointAgreesVo;
}
