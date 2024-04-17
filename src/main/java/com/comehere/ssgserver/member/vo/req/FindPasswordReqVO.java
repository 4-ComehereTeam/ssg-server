package com.comehere.ssgserver.member.vo.req;

import lombok.Getter;

@Getter
public class FindPasswordReqVO {

	private String email;

	private String name;

	private String newPassword;
}
