package com.comehere.ssgserver.member.vo.response;

import java.util.UUID;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class SignInResponseVO {

	private String accessToken;
	private String email;
	private String name;
	private String signinId;
	private UUID uuid;
}
