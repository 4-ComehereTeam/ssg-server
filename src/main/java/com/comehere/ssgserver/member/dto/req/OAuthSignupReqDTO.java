package com.comehere.ssgserver.member.dto.req;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class OAuthSignupReqDTO {

	private String name;

	private String email;

	private String id;

	private String phone;

	private Short gender;

}
