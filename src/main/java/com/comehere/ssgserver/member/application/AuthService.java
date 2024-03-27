package com.comehere.ssgserver.member.application;

import com.comehere.ssgserver.member.dto.SignInResponseDTO;
import com.comehere.ssgserver.member.vo.JoinRequestVo;
import com.comehere.ssgserver.member.vo.SignInRequestVo;

public interface AuthService {
	void signUp(JoinRequestVo joinRequestVo);

	SignInResponseDTO signIn(SignInRequestVo signInRequestVo);
}
