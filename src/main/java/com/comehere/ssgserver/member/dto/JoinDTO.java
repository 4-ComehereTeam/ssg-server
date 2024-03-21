package com.comehere.ssgserver.member.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class JoinDTO {

	private String loginId;
	private String password;

	// private String loginId;
	//
	// private String password;
	//
	// private String name;
	//
	// private String phoneNumber;
	//
	// private String email;
	//
	// private LocalDateTime signupTime;
	//
	// private String birthday;
	//
	// private Short gender;
}
