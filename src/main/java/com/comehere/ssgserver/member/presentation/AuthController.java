package com.comehere.ssgserver.member.presentation;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.comehere.ssgserver.common.response.BaseResponse;
import com.comehere.ssgserver.member.application.AuthService;
import com.comehere.ssgserver.member.vo.JoinRequestVo;

@RestController
@RequestMapping("/api/v1/members")
public class AuthController {

	private final AuthService authService;

	public AuthController(AuthService authService) {
		this.authService = authService;
	}

	@PostMapping("/join")
	public BaseResponse<?> joinProcess(@RequestBody JoinRequestVo joinRequestVo) {

		System.out.println("joinReqeustVo: " + joinRequestVo.getSigninId());
		authService.signUp(joinRequestVo);
		return new BaseResponse<>();
	}

}
