package com.comehere.ssgserver.member.presentation;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.comehere.ssgserver.member.application.JoinService;
import com.comehere.ssgserver.member.dto.JoinDTO;

@Controller
@ResponseBody
public class JoinController {

	private final JoinService joinService;

	public JoinController(JoinService joinService) {
		this.joinService = joinService;
	}

	@PostMapping("/join")
	public String joinProcess(JoinDTO joinDTO) {

		System.out.println("joinDTO: " + joinDTO.getLoginId());

		joinService.joinProcess(joinDTO);
		return "ok";
	}
}
