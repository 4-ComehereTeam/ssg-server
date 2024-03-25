package com.comehere.ssgserver.member.vo;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class AgreeVo {
	private Boolean marketingAgree;

	private Boolean emailAgree;

	private Boolean smsAgree;

	private Boolean mailAgree;

	private Boolean callAgree;
}
