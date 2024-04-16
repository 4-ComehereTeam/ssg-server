package com.comehere.ssgserver.member.dto.resp;

import java.util.UUID;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class SigninRespDTO {

	private String accessToken;
	private String email;
	private String name;
	private String signinId;
	private UUID uuid;
}
