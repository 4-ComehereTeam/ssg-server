package com.comehere.ssgserver.member.presentation;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.comehere.ssgserver.common.response.BaseResponse;
import com.comehere.ssgserver.member.application.AuthService;
import com.comehere.ssgserver.member.dto.SignInResponseDTO;
import com.comehere.ssgserver.member.vo.JoinRequestVo;
import com.comehere.ssgserver.member.vo.SignInRequestVo;

import jakarta.servlet.http.HttpServletResponse;

@RestController
@RequestMapping("/api/v1/members")
public class AuthController {

	private final AuthService authService;

	public AuthController(AuthService authService) {
		this.authService = authService;
	}

	@PostMapping("/signUp")
	public BaseResponse<?> joinProcess(@RequestBody JoinRequestVo joinRequestVo) {

		System.out.println("joinReqeustVo: " + joinRequestVo.getSigninId());
		authService.signUp(joinRequestVo);
		return new BaseResponse<>();
	}

	@PostMapping("/signIn")
	public BaseResponse<?> signIn(@RequestBody SignInRequestVo signInRequestVo, HttpServletResponse response) {

		SignInResponseDTO signInResponseDTO = authService.signIn(signInRequestVo);

		// jwt 토큰을 http 응답 헤더에 추가
		response.addHeader("accessToken", "Bearer " + signInResponseDTO.getAccessToken());

		return new BaseResponse<>(signInResponseDTO);
	}
}
