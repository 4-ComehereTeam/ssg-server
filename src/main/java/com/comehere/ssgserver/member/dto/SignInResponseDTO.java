package com.comehere.ssgserver.member.dto;

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
public class SignInResponseDTO {

	private String accessToken;
	private String name;
	private UUID uuid;
}
