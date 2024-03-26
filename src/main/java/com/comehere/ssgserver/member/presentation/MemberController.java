package com.comehere.ssgserver.member.presentation;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.comehere.ssgserver.common.response.BaseResponse;
import com.comehere.ssgserver.common.response.BaseResponseStatus;
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

	// id 중복 체크
	@GetMapping("/signInId/check")

	public BaseResponse<?> checkUserSignInIdDuplication(@RequestParam String signinId) {
		return new BaseResponse<>(memberService.checkUserSignInIdDuplication(signinId));
	}

	// email 중복 체크
	@PostMapping("/email/check")
	public BaseResponse<?> checkUserEmailDuplication(@RequestBody String email) {

		boolean isDuplicated = memberService.checkUserEmailDuplication(email);

		if (isDuplicated) {
			//이메일이 이미 사용 중인 경우
			return new BaseResponse<>(BaseResponseStatus.DUPLICATE_EMAIL);
		} else {
			return new BaseResponse<>(HttpStatus.OK, true, "사용 가능한 이메일입니다.", 0, true);
		}

	}
}
