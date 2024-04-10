package com.comehere.ssgserver.member.dto.resp;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class FindSigninIdRespDTO {

	private Short status;

	private String signinId;

	public FindSigninIdRespDTO(Short status, String signinId) {
		this.status = status;
		this.signinId = signinId;
	}
}
