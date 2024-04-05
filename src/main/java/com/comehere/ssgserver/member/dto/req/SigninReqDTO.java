package com.comehere.ssgserver.member.dto.req;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class SigninReqDTO {
	private String signinId;
	private String password;
}
