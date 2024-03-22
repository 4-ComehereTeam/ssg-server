package com.comehere.ssgserver.member.presentation;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import io.swagger.v3.oas.annotations.tags.Tag;

@Controller
@ResponseBody
@RequestMapping("/api/v1/members")
@Tag(name = "members", description = "회원 관리 컨트롤러")
public class MemberController {

	@GetMapping("/")
	public String main() {
		return "Main Controller";
	}
}
