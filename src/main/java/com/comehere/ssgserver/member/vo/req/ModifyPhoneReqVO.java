package com.comehere.ssgserver.member.vo.req;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.Getter;

@Getter
public class ModifyPhoneReqVO {

	@NotBlank(message = "휴대폰 번호는 필수 입력 값입니다.")
	@Pattern(regexp = "^01(0|1|[6-9])(\\d{3,4})(\\d{4})$", message = "10 ~ 11 자리의 숫자만 입력 가능합니다.")
	private String newPhone;
}
