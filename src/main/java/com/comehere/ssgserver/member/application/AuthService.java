package com.comehere.ssgserver.member.application;

import com.comehere.ssgserver.member.dto.request.CheckStatusRequestDTO;
import com.comehere.ssgserver.member.dto.request.SigninRequestDTO;
import com.comehere.ssgserver.member.dto.response.CheckResignCountResponseDTO;
import com.comehere.ssgserver.member.dto.response.SigninResponseDTO;
import com.comehere.ssgserver.member.vo.request.JoinRequestVO;

public interface AuthService {
	void signUp(JoinRequestVO joinRequestVo);

	SigninResponseDTO signIn(SigninRequestDTO signinRequestDto);

	boolean checkUserEmailDuplication(String email);

	boolean checkUserSignInIdDuplication(String signInId);

	boolean checkDormancy(CheckStatusRequestDTO checkStatusRequestDto);

	CheckResignCountResponseDTO checkResignCount(CheckStatusRequestDTO checkStatusRequestDto);
}
