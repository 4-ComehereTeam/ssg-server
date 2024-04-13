package com.comehere.ssgserver.member.dto.resp;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class FindSigninIdRespDTO {

	private Short status;

	private String signinId;

}
