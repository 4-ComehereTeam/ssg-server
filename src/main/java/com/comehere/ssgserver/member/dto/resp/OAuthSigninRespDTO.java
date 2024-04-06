package com.comehere.ssgserver.member.dto.resp;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class OAuthSigninRespDTO {
	
	private String accessToken;
}
