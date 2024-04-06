package com.comehere.ssgserver.member.application;

import com.comehere.ssgserver.member.dto.req.OAuthSigninReqDTO;
import com.comehere.ssgserver.member.dto.req.OAuthSignupReqDTO;
import com.comehere.ssgserver.member.dto.resp.OAuthSigninRespDTO;

public interface OAuthService {

	// OAuth 회원가입
	Boolean signup(OAuthSignupReqDTO oAuthSignupReqDto);

	OAuthSigninRespDTO signin(OAuthSigninReqDTO oAuthSigninReqDto);
}
