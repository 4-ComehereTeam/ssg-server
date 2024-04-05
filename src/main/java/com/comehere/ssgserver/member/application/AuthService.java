package com.comehere.ssgserver.member.application;

import com.comehere.ssgserver.member.dto.request.CheckStateRequestDTO;
import com.comehere.ssgserver.member.dto.request.SigninRequestDTO;
import com.comehere.ssgserver.member.dto.response.CheckResignCountResponseDTO;
import com.comehere.ssgserver.member.dto.response.SigninResponseDTO;
import com.comehere.ssgserver.member.vo.request.JoinRequestVO;

public interface AuthService {
	void signUp(JoinRequestVO joinRequestVo);

	SigninResponseDTO signIn(SigninRequestDTO signinRequestDto);

	boolean checkUserEmailDuplication(String email);

	boolean checkUserSignInIdDuplication(String signInId);

	boolean checkDormancy(CheckStateRequestDTO checkStateRequestDto);

	boolean checkResign(CheckStateRequestDTO checkStateRequestDto);

	CheckResignCountResponseDTO checkResignCount(CheckStateRequestDTO checkStateRequestDto);
}
