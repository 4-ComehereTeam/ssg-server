package com.comehere.ssgserver.member.presentation;

import java.util.UUID;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.comehere.ssgserver.common.response.BaseResponse;
import com.comehere.ssgserver.common.security.jwt.JWTUtil;
import com.comehere.ssgserver.member.application.MemberService;
import com.comehere.ssgserver.member.dto.FindSigninIdDTO;
import com.comehere.ssgserver.member.dto.ModifyPwdDTO;
import com.comehere.ssgserver.member.vo.request.ModifyPwdRequestVO;
import com.comehere.ssgserver.member.vo.response.FindSigninIdResponseVO;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Controller
@ResponseBody
@RequestMapping("/api/v1/members")
@AllArgsConstructor
@Slf4j
@Tag(name = "Members", description = "회원 관리 컨트롤러")
public class MemberController {

	private final MemberService memberService;
	private JWTUtil jwtUtil;
	private final ModelMapper modelMapper;

	@GetMapping("/")
	public String main() {
		return "Main Controller";
	}

	@PostMapping("/modify/password")
	@Operation(summary = "비밀번호 변경")
	public BaseResponse<?> userModifyPassword(@RequestHeader("Authorization") String accessToken,
			@RequestBody ModifyPwdRequestVO modifyPwdRequestVo) {

		ModifyPwdDTO modifyPwdDTO = modelMapper.map(modifyPwdRequestVo, ModifyPwdDTO.class);
		UUID userUuid = jwtUtil.getUuidByAuthorization(accessToken);
		return memberService.modifyPassword(userUuid, modifyPwdDTO);
	}

	@GetMapping("/find/signinId")
	@Operation(summary = "아이디 찾기")
	public BaseResponse<?> userFindSigninId(@RequestHeader("Authorization") String accessToken) {

		FindSigninIdDTO findSigninIdDTO = memberService.findSigninId(jwtUtil.getUuidByAuthorization(accessToken));
		return new BaseResponse<>(modelMapper.map(findSigninIdDTO, FindSigninIdResponseVO.class));
	}
}
