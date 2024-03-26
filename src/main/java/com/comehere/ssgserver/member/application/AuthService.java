package com.comehere.ssgserver.member.application;

import com.comehere.ssgserver.member.vo.JoinRequestVo;
import com.comehere.ssgserver.member.vo.SignInRequestVo;
import com.comehere.ssgserver.member.vo.SignInResponseVo;

public interface AuthService {
	void signUp(JoinRequestVo joinRequestVo);

	SignInResponseVo signIn(SignInRequestVo signInRequestVo);
}
