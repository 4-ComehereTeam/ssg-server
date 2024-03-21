package com.comehere.ssgserver.member.presentation;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@ResponseBody
public class MemberController {

	@GetMapping("/")
	public String main() {
		return "Main Controller";
	}
}
