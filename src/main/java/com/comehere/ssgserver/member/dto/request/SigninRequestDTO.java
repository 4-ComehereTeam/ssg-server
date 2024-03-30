package com.comehere.ssgserver.member.dto.request;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class SigninRequestDTO {
	private String signinId;
	private String password;
}
