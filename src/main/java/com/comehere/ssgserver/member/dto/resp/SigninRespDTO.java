package com.comehere.ssgserver.member.dto.resp;

import java.util.UUID;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@Builder
public class SigninRespDTO {

	private String accessToken;
	private String email;
	private String name;
	private String signinId;
	private UUID uuid;
}
