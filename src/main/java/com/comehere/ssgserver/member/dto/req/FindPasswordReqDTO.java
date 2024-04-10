package com.comehere.ssgserver.member.dto.req;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class FindPasswordReqDTO {

	private String email;

	private String name;

	private String newPassword;
}
