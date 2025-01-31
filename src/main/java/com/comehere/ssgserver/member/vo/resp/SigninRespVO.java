package com.comehere.ssgserver.member.vo.resp;

import java.util.UUID;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class SigninRespVO {

	private String accessToken;
	private String email;
	private String name;
	private String signinId;
	private UUID uuid;

}
