package com.comehere.ssgserver.member.presentation;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.comehere.ssgserver.common.response.BaseResponse;
import com.comehere.ssgserver.common.security.jwt.JWTUtil;
import com.comehere.ssgserver.member.application.MemberService;
import com.comehere.ssgserver.member.dto.FindSigninIdDTO;
import com.comehere.ssgserver.member.dto.ModifyEmailDTO;
import com.comehere.ssgserver.member.dto.ModifyPhoneDTO;
import com.comehere.ssgserver.member.dto.ModifyPwdDTO;
import com.comehere.ssgserver.member.vo.req.ModifyEmailReqVO;
import com.comehere.ssgserver.member.vo.req.ModifyPhoneReqVO;
import com.comehere.ssgserver.member.vo.req.ModifyPwdReqVO;
import com.comehere.ssgserver.member.vo.resp.FindSigninIdRespVO;

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

	@GetMapping("/find/signinId")
	@Operation(summary = "아이디 찾기")
	public BaseResponse<FindSigninIdRespVO> userFindSigninId(@RequestHeader("Authorization") String accessToken) {

		FindSigninIdDTO findSigninIdDTO = memberService.findSigninId(jwtUtil.getUuidByAuthorization(accessToken));
		return new BaseResponse<>(modelMapper.map(findSigninIdDTO, FindSigninIdRespVO.class));
	}
	
	@PutMapping("/modify/password")
	@Operation(summary = "비밀번호 변경")
	public BaseResponse<Boolean> userModifyPassword(@RequestHeader("Authorization") String accessToken,
			@RequestBody ModifyPwdReqVO modifyPwdReqVo) {

		ModifyPwdDTO modifyPwdDTO = modelMapper.map(modifyPwdReqVo, ModifyPwdDTO.class);
		return new BaseResponse<>(
				memberService.modifyPassword(jwtUtil.getUuidByAuthorization(accessToken), modifyPwdDTO));
	}

	@PutMapping("/modify/email")
	@Operation(summary = "이메일 변경")
	public BaseResponse<Boolean> userModifyEmail(@RequestHeader("Authorization") String accessToken,
			@RequestBody ModifyEmailReqVO modifyEmailReqVo) {

		ModifyEmailDTO modifyEmailDTO = modelMapper.map(modifyEmailReqVo, ModifyEmailDTO.class);
		return new BaseResponse<>(
				memberService.modifyEmail(jwtUtil.getUuidByAuthorization(accessToken), modifyEmailDTO));
	}

	@PutMapping("/modify/phone")
	@Operation(summary = "전화번호 변경")
	public BaseResponse<Boolean> userModifyPhone(@RequestHeader("Authorization") String accessToken,
			@RequestBody ModifyPhoneReqVO modifyPhoneReqVo) {

		ModifyPhoneDTO modifyPhoneDTO = modelMapper.map(modifyPhoneReqVo, ModifyPhoneDTO.class);

		return new BaseResponse<>(
				memberService.modifyPhone(jwtUtil.getUuidByAuthorization(accessToken), modifyPhoneDTO));
	}

	@PutMapping("resign")
	@Operation(summary = "회원 탈퇴")
	public BaseResponse<Boolean> userResign(@RequestHeader("Authorization") String accessToken) {
		return new BaseResponse<>(memberService.resignMember(jwtUtil.getUuidByAuthorization(accessToken)));
	}
}
