package com.comehere.ssgserver.member.presentation;

import java.util.Map;

import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.comehere.ssgserver.common.response.BaseResponse;
import com.comehere.ssgserver.common.response.BaseResponseStatus;
import com.comehere.ssgserver.member.application.AuthService;
import com.comehere.ssgserver.member.dto.request.CheckStatusRequestDTO;
import com.comehere.ssgserver.member.dto.request.SigninRequestDTO;
import com.comehere.ssgserver.member.dto.response.SigninResponseDTO;
import com.comehere.ssgserver.member.vo.request.CheckStatusRequestVO;
import com.comehere.ssgserver.member.vo.request.JoinRequestVO;
import com.comehere.ssgserver.member.vo.request.SigninRequestVO;
import com.comehere.ssgserver.member.vo.response.CheckResignCountResponseVO;
import com.comehere.ssgserver.member.vo.response.SigninResponseVO;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.servlet.http.HttpServletResponse;
import lombok.AllArgsConstructor;

@RestController
@AllArgsConstructor
@RequestMapping("/api/v1/auth")
@Tag(name = "Auth", description = "회원 인증 컨트롤러")
public class AuthController {

	private final AuthService authService;
	private final ModelMapper modelMapper;

	@PostMapping("/signUp")
	@Operation(summary = "회원가입")
	public BaseResponse<?> joinProcess(@RequestBody JoinRequestVO joinRequestVo) {

		System.out.println("joinReqeustVo: " + joinRequestVo.getSigninId());
		authService.signUp(joinRequestVo);
		return new BaseResponse<>();
	}

	@PostMapping("/signIn")
	@Operation(summary = "로그인", description = "로그인 성공 시 JWT 토큰 발급.")
	public BaseResponse<?> signIn(@RequestBody SigninRequestVO signinRequestVo, HttpServletResponse response) {
		try {

			SigninRequestDTO signinRequestDTO = modelMapper.map(signinRequestVo, SigninRequestDTO.class);
			SigninResponseDTO signinResponseDTO = authService.signIn(signinRequestDTO);

			// jwt 토큰을 http 응답 헤더에 추가
			response.addHeader("accessToken", "Bearer " + signinResponseDTO.getAccessToken());

			return new BaseResponse<>(modelMapper.map(signinResponseDTO, SigninResponseVO.class));
		} catch (BadCredentialsException e) {
			// 아이디 또는 비밀번호가 잘못된 경우
			return new BaseResponse<>(BaseResponseStatus.FAILED_TO_LOGIN);
		} catch (UsernameNotFoundException e) {
			// 존재하지 않는 회원인 경우
			return new BaseResponse<>(BaseResponseStatus.NO_EXIST_MEMBERS);
		} catch (Exception e) {
			// 기타 에러 발생 시
			return new BaseResponse<>(BaseResponseStatus.INTERNAL_SERVER_ERROR);
		}
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
	@Operation(summary = "이메일 중복확인", description = "이메일 중복확인을 통해서 가입된 회원인지 조회")
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

	@PostMapping("/resign")
	@Operation(summary = "탈퇴 횟수 조회")
	public BaseResponse<?> checkUserResignCount(@RequestBody CheckStatusRequestVO checkStatusRequestVO) {

		CheckStatusRequestDTO checkStatusRequestDTO = modelMapper.map(checkStatusRequestVO,
				CheckStatusRequestDTO.class);

		return new BaseResponse<>(modelMapper.map(authService.checkResignCount(checkStatusRequestDTO),
				CheckResignCountResponseVO.class));
	}

	@PostMapping("/dormancy")
	@Operation(summary = "휴면 계정 여부 조회")
	public BaseResponse<?> checkUserDormancy(@RequestBody CheckStatusRequestVO checkStatusRequestVO) {

		boolean isDormancy = authService.checkDormancy(
				modelMapper.map(checkStatusRequestVO, CheckStatusRequestDTO.class));

		if (isDormancy) {
			return new BaseResponse<>(true);
		} else {
			return new BaseResponse<>(false);
		}
	}

}
