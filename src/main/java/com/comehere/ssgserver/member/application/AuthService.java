package com.comehere.ssgserver.member.application;

import com.comehere.ssgserver.member.dto.request.SigninRequestDTO;
import com.comehere.ssgserver.member.dto.response.SigninResponseDTO;
import com.comehere.ssgserver.member.vo.request.JoinRequestVO;

public interface AuthService {
	void signUp(JoinRequestVO joinRequestVo);

	SigninResponseDTO signIn(SigninRequestDTO signinRequestDto);

	public boolean checkUserEmailDuplication(String email);

	public boolean checkUserSignInIdDuplication(String signInId);
}
