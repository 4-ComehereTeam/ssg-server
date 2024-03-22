package com.comehere.ssgserver.member.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class JoinDTO {

	private String signinId;

	private String password;

	private String name;

	private String phone;

	private String email;

	private AddressInfoDTO addressInfodto;

	private AgreeDTO agreedto;
}
