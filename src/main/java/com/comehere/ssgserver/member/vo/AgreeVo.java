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
	private Short marketingAgree;

	private Short emailAgree;

	private Short smsAgree;

	private Short mailAgree;

	private Short callAgree;
}
