package com.comehere.ssgserver.member.dto.req;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class FindPasswordReqDTO {

	private String email;

	private String name;

	private String newPassword;

	public void updatePasswordEncoder(String newPassword) {
		this.newPassword = newPassword;
	}
}
