package com.comehere.ssgserver.member.vo.req;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class SigninReqVO {

	private String signinId;
	private String password;
}
