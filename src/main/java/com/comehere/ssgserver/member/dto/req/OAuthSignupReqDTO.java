package com.comehere.ssgserver.member.dto.req;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class OAuthSignupReqDTO {

	private String name;

	private String email;

	private String id;

	private String phone;

	private Short gender;

}
