package com.comehere.ssgserver.member.presentation;

import org.modelmapper.ModelMapper;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.comehere.ssgserver.common.response.BaseResponse;
import com.comehere.ssgserver.member.application.OAuthService;
import com.comehere.ssgserver.member.dto.req.OAuthSigninReqDTO;
import com.comehere.ssgserver.member.dto.req.OAuthSignupReqDTO;
import com.comehere.ssgserver.member.dto.resp.OAuthSigninRespDTO;
import com.comehere.ssgserver.member.vo.req.OAuthSigninReqVO;
import com.comehere.ssgserver.member.vo.req.OAuthSignupReqVO;
import com.comehere.ssgserver.member.vo.resp.OAuthSigninRespVO;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;

@RestController
@AllArgsConstructor
@RequestMapping("/api/v1/oauth")
@Tag(name = "OAuth", description = "OAuth 인증 컨트롤러")
public class OAuthController {

	private final OAuthService oauthService;
	private final ModelMapper modelMapper;

	@PostMapping("/signup")
	@Operation(summary = "OAuth 회원가입")
	public BaseResponse<Boolean> oauthJoinProcess(@RequestBody @Valid OAuthSignupReqVO oauthSignupReqVo) {

		return new BaseResponse<>(oauthService.signup(modelMapper.map(oauthSignupReqVo, OAuthSignupReqDTO.class)));
	}

	@PostMapping("/signin")
	@Operation(summary = "OAuth 로그인")
	public BaseResponse<OAuthSigninRespVO> oauthSignIn(@RequestBody OAuthSigninReqVO oAuthSigninReqVO,
			HttpServletResponse response) {

		OAuthSigninRespDTO oAuthSigninRespDTO = oauthService.signin(
				modelMapper.map(oAuthSigninReqVO, OAuthSigninReqDTO.class));

		response.addHeader("accessToken", "Bearer " + oAuthSigninRespDTO.getAccessToken());

		return new BaseResponse<>(modelMapper.map(oAuthSigninRespDTO, OAuthSigninRespVO.class));
	}
}
