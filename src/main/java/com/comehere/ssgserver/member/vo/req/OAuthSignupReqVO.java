package com.comehere.ssgserver.member.vo.req;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Getter;

@Getter
public class OAuthSignupReqVO {

	@NotBlank
	@Size(min = 2, max = 20, message = "2자 이상, 20자 이하만 가능합니다.")
	private String name;

	@NotBlank(message = "이메일은 필수 입력 값입니다.")
	@Email(message = "이메일 형식이 올바르지 않습니다.")
	private String email;

	// 실제 테이블에는 signinId에 저장
	private String id;

	@NotBlank(message = "휴대폰 번호는 필수 입력 값입니다.")
	@Pattern(regexp = "^01(0|1|[6-9])(\\d{3,4})(\\d{4})$", message = "10 ~ 11 자리의 숫자만 입력 가능합니다.")
	private String phone;

	private Short gender;
}
