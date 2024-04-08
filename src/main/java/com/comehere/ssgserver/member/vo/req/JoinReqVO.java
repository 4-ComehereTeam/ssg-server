package com.comehere.ssgserver.member.vo.req;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class JoinReqVO {

	@NotBlank(message = "아이디는 필수 입력 값입니다.")
	private String signinId;

	@NotBlank(message = "비밀번호는 필수 입력 값입니다.")
	@Pattern(regexp = "(?=.*[0-9])(?=.*[a-zA-Z])(?=\\S+$).{8,20}", message = "비밀번호는 8~16자 영문 대 소문자, 숫자, 특수문자를 사용하세요.")
	private String password;

	@NotBlank
	@Size(min = 2, max = 20, message = "2자 이상, 20자 이하만 가능합니다.")
	private String name;

	@NotBlank(message = "휴대폰 번호는 필수 입력 값입니다.")
	@Pattern(regexp = "^01(0|1|[6-9])-(\\d{3}|\\d{4})-(\\d{4})$", message = "10 ~ 11 자리의 숫자만 입력 가능합니다.")
	private String phone;

	@NotBlank(message = "이메일은 필수 입력 값입니다.")
	@Email(message = "이메일 형식이 올바르지 않습니다.")
	private String email;

	// 0 : 남/ 1 : 여
	private Short gender;

	private AddressInfoVO addressInfoVo;

	private SsgcomAgreesVO ssgcomAgreesVo;

	private SsgPointAgreesVO ssgPointAgreesVo;
}
