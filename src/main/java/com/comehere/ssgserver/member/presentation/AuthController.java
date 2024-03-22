package com.comehere.ssgserver.member.presentation;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.comehere.ssgserver.member.application.AuthService;
import com.comehere.ssgserver.member.dto.JoinDTO;

@RestController
@RequestMapping("/api/v1/members")
public class JoinController {

	private final AuthService authService;

	public JoinController(AuthService authService) {
		this.authService = authService;
	}

	@PostMapping("/join")
	public String joinProcess(@RequestBody JoinDTO joinDTO) {

		System.out.println("joinDTO: " + joinDTO.getSigninId());

		authService.joinProcess(joinDTO);
		return "ok";
	}
}
