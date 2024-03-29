package com.comehere.ssgserver.member.application;

import com.comehere.ssgserver.member.vo.request.JoinRequestVO;
import com.comehere.ssgserver.member.vo.request.SignInRequestVO;
import com.comehere.ssgserver.member.vo.response.SignInResponseVO;

public interface AuthService {
	void signUp(JoinRequestVO joinRequestVo);

	SignInResponseVO signIn(SignInRequestVO signInRequestVo);

	public boolean checkUserEmailDuplication(String email);

	public boolean checkUserSignInIdDuplication(String signInId);
}
