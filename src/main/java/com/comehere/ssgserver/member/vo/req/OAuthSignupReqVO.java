package com.comehere.ssgserver.member.vo.req;

import lombok.Getter;

@Getter
public class OAuthSignupReqVO {

	private String name;

	private String email;

	// 실제 테이블에는 signinId에 저장
	private String id;

	private String phone;

	private Short gender;
}
