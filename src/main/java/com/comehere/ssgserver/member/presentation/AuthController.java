package com.comehere.ssgserver.member.presentation;

import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.comehere.ssgserver.common.response.BaseResponse;
import com.comehere.ssgserver.common.response.BaseResponseStatus;
import com.comehere.ssgserver.member.application.AuthService;
import com.comehere.ssgserver.member.vo.request.JoinRequestVO;
import com.comehere.ssgserver.member.vo.request.SignInRequestVO;
import com.comehere.ssgserver.member.vo.response.SignInResponseVO;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.servlet.http.HttpServletResponse;

@RestController
@RequestMapping("/api/v1/auth")
@Tag(name = "Auth", description = "회원 인증 컨트롤러")
public class AuthController {

	private final AuthService authService;

	public AuthController(AuthService authService) {
		this.authService = authService;
	}

	@PostMapping("/signUp")
	@Operation(summary = "회원가입")
	public BaseResponse<?> joinProcess(@RequestBody JoinRequestVO joinRequestVo) {

		System.out.println("joinReqeustVo: " + joinRequestVo.getSigninId());
		authService.signUp(joinRequestVo);
		return new BaseResponse<>();
	}

	@PostMapping("/signIn")
	@Operation(summary = "로그인",
			description = "로그인 성공 시 JWT 토큰 발급.")
	public BaseResponse<?> signIn(@RequestBody SignInRequestVO signInRequestVo, HttpServletResponse response) {

		SignInResponseVO signInResponseVo = authService.signIn(signInRequestVo);

		// jwt 토큰을 http 응답 헤더에 추가
		response.addHeader("accessToken", "Bearer " + signInResponseVo.getAccessToken());

		return new BaseResponse<>(signInResponseVo);
	}

	@GetMapping("/signInId/check")
	@Operation(summary = "로그인 Id 중복확인")
	public BaseResponse<?> checkUserSignInIdDuplication(@RequestParam String signinId) {

		boolean isDuplicated = authService.checkUserSignInIdDuplication(signinId);
		if (isDuplicated) {
			return new BaseResponse<>(BaseResponseStatus.DUPLICATED_MEMBERS);
		} else {
			return new BaseResponse<>(HttpStatus.OK, true, "사용 가능한 로그인 아이디입니다.", 200, true);
		}
	}

	@PostMapping("/email/check")
	@Operation(summary = "이메일 중복확인")
	public BaseResponse<?> checkUserEmailDuplication(@RequestBody Map<String, String> email) {

		String emailStr = email.get("email");
		boolean isDuplicated = authService.checkUserEmailDuplication(emailStr);

		if (isDuplicated) {
			//이메일이 이미 사용 중인 경우
			return new BaseResponse<>(BaseResponseStatus.DUPLICATE_EMAIL);
		} else {
			return new BaseResponse<>(HttpStatus.OK, true, "사용 가능한 이메일입니다.", 200, true);
		}
	}
}
