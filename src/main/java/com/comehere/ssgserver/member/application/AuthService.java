package com.comehere.ssgserver.member.application;

import com.comehere.ssgserver.member.dto.req.CheckStateReqDTO;
import com.comehere.ssgserver.member.dto.req.SigninReqDTO;
import com.comehere.ssgserver.member.dto.resp.CheckResignCountRespDTO;
import com.comehere.ssgserver.member.dto.resp.SigninRespDTO;
import com.comehere.ssgserver.member.vo.req.JoinReqVO;

public interface AuthService {
	void signUp(JoinReqVO joinReqVo);

	SigninRespDTO signIn(SigninReqDTO signinReqDto);

	boolean checkUserEmailDuplication(String email);

	boolean checkUserSignInIdDuplication(String signInId);

	boolean checkDormancy(CheckStateReqDTO checkStateReqDto);

	boolean checkResign(CheckStateReqDTO checkStateReqDto);

	CheckResignCountRespDTO checkResignCount(CheckStateReqDTO checkStateReqDto);
}
