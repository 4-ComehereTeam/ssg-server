package com.comehere.ssgserver.member.vo.response;

import java.util.UUID;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Builder
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class SigninResponseVO {

	private String accessToken;
	private String email;
	private String name;
	private String signinId;
	private UUID uuid;

	@Builder
	public SigninResponseVO(String accessToken, String email, String name, String signinId, UUID uuid) {

		this.accessToken = accessToken;
		this.email = email;
		this.name = name;
		this.signinId = signinId;
		this.uuid = uuid;
	}
}
