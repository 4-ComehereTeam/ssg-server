package com.comehere.ssgserver.member.presentation;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.comehere.ssgserver.common.response.BaseResponse;
import com.comehere.ssgserver.member.application.MemberService;

import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;

@Controller
@ResponseBody
@RequestMapping("/api/v1/members")
@AllArgsConstructor
@Tag(name = "members", description = "회원 관리 컨트롤러")
public class MemberController {

	private final MemberService memberService;

	@GetMapping("/")
	public String main() {
		return "Main Controller";
	}

	@GetMapping("/signInId/check")
	public BaseResponse<?> checkUserSignInIdDuplication(@RequestParam String signinId) {
		return new BaseResponse<>(memberService.checkUserSignInIdDuplication(signinId));
	}
}
