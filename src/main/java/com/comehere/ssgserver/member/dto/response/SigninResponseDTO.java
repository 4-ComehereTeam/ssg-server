package com.comehere.ssgserver.member.dto.response;

import java.util.UUID;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@Builder
public class SigninResponseDTO {

	private String accessToken;
	private String email;
	private String name;
	private String signinId;
	private UUID uuid;
}
