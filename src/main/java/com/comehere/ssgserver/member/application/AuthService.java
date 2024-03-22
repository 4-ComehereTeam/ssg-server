package com.comehere.ssgserver.member.application;

import com.comehere.ssgserver.member.vo.JoinRequestVo;

public interface AuthService {
	void signUp(JoinRequestVo joinRequestVo);
}
