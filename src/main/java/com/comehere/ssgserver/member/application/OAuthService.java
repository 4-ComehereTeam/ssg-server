package com.comehere.ssgserver.member.application;

import com.comehere.ssgserver.member.dto.req.OAuthSignupReqDTO;

public interface OAuthService {

	// OAuth 회원가입
	Boolean signup(OAuthSignupReqDTO oAuthSignupReqDto);
}
