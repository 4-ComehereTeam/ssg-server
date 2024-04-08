package com.comehere.ssgserver.member.presentation;

import java.util.Map;

import org.modelmapper.ModelMapper;
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
import com.comehere.ssgserver.member.dto.req.CheckStateReqDTO;
import com.comehere.ssgserver.member.dto.req.JoinReqDTO;
import com.comehere.ssgserver.member.dto.req.SigninReqDTO;
import com.comehere.ssgserver.member.dto.resp.SigninRespDTO;
import com.comehere.ssgserver.member.vo.req.CheckStateReqVO;
import com.comehere.ssgserver.member.vo.req.JoinReqVO;
import com.comehere.ssgserver.member.vo.req.SigninReqVO;
import com.comehere.ssgserver.member.vo.resp.CheckResignCountRespVO;
import com.comehere.ssgserver.member.vo.resp.SigninRespVO;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;

@RestController
@AllArgsConstructor
@RequestMapping("/api/v1/auth")
@Tag(name = "Auth", description = "회원 인증 컨트롤러")
public class AuthController {

	private final AuthService authService;
	private final ModelMapper modelMapper;
	private final ModelMapper joinModelMapper;

	@PostMapping("/signUp")
	@Operation(summary = "회원가입")
	public BaseResponse<Void> joinProcess(@RequestBody @Valid JoinReqVO joinReqVo) {

		authService.signUp(joinModelMapper.map(joinReqVo, JoinReqDTO.class));
		return new BaseResponse<>();
	}

	@PostMapping("/signIn")
	@Operation(summary = "로그인", description = "로그인 성공 시 JWT 토큰 발급.")
	public BaseResponse<SigninRespVO> signIn(@RequestBody SigninReqVO signinReqVo,
			HttpServletResponse response) {

		SigninRespDTO signinRespDTO = authService.signIn(
				modelMapper.map(signinReqVo, SigninReqDTO.class));

		// jwt 토큰을 http 응답 헤더에 추가
		response.addHeader("accessToken", "Bearer " + signinRespDTO.getAccessToken());

		return new BaseResponse<>(modelMapper.map(signinRespDTO, SigninRespVO.class));
	}

	@GetMapping("/signInId/check")
	@Operation(summary = "로그인 Id 중복확인")
	public BaseResponse<Boolean> checkUserSignInIdDuplication(@RequestParam String signinId) {

		boolean isDuplicated = authService.checkUserSignInIdDuplication(signinId);
		if (isDuplicated) {
			return new BaseResponse<>(BaseResponseStatus.DUPLICATED_MEMBERS);
		} else {
			return new BaseResponse<>(HttpStatus.OK, true, "사용 가능한 로그인 아이디입니다.", 200, true);
		}
	}

	@PostMapping("/email/check")
	@Operation(summary = "이메일 중복확인", description = "이메일 중복확인을 통해서 가입된 회원인지 조회")
	public BaseResponse<Boolean> checkUserEmailDuplication(@RequestBody Map<String, String> email) {

		String emailStr = email.get("email");
		boolean isDuplicated = authService.checkUserEmailDuplication(emailStr);

		if (isDuplicated) {
			//이메일이 이미 사용 중인 경우
			return new BaseResponse<>(BaseResponseStatus.DUPLICATE_EMAIL);
		} else {
			return new BaseResponse<>(HttpStatus.OK, true, "사용 가능한 이메일입니다.", 200, true);
		}
	}

	@PostMapping("/resign/count")
	@Operation(summary = "탈퇴 횟수 조회")
	public BaseResponse<CheckResignCountRespVO> checkUserResignCount(
			@RequestBody CheckStateReqVO checkStateReqVO) {

		return new BaseResponse<>(modelMapper.map(authService.checkResignCount(modelMapper.map(checkStateReqVO,
						CheckStateReqDTO.class)),
				CheckResignCountRespVO.class));
	}

	@PostMapping("/dormancy/state")
	@Operation(summary = "휴면 계정 여부 조회")
	public BaseResponse<Boolean> checkUserDormancyState(@RequestBody CheckStateReqVO checkStateReqVO) {

		boolean isDormancy = authService.checkDormancy(
				modelMapper.map(checkStateReqVO, CheckStateReqDTO.class));

		if (isDormancy) {
			return new BaseResponse<>(true);
		} else {
			return new BaseResponse<>(false);
		}
	}

	@PostMapping("/resign/state")
	@Operation(summary = "탈퇴 여부 조회")
	public BaseResponse<Boolean> checkUserResignState(@RequestBody CheckStateReqVO checkStateReqVO) {

		boolean isResign = authService.checkResign(
				modelMapper.map(checkStateReqVO, CheckStateReqDTO.class));

		if (isResign) {
			return new BaseResponse<>(true);
		} else {
			return new BaseResponse<>(false);
		}
	}
}
