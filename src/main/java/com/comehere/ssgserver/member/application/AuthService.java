package com.comehere.ssgserver.member.application;

import com.comehere.ssgserver.member.dto.req.CheckStateReqDTO;
import com.comehere.ssgserver.member.dto.req.JoinReqDTO;
import com.comehere.ssgserver.member.dto.req.SigninReqDTO;
import com.comehere.ssgserver.member.dto.resp.CheckResignCountRespDTO;
import com.comehere.ssgserver.member.dto.resp.SigninRespDTO;

public interface AuthService {

	//회원가입
	void signUp(JoinReqDTO joinReqDTO);

	//로그인
	SigninRespDTO signIn(SigninReqDTO signinReqDto);

	//이메일 중복 체크
	boolean checkUserEmailDuplication(String email);

	//아이디 중복 체크
	boolean checkUserSignInIdDuplication(String signInId);

	//휴면 계정 체크
	boolean checkDormancy(CheckStateReqDTO checkStateReqDto);

	//탈퇴 계정 체크
	boolean checkResign(CheckStateReqDTO checkStateReqDto);

	//탈퇴 횟수 조회
	CheckResignCountRespDTO checkResignCount(CheckStateReqDTO checkStateReqDto);
}
