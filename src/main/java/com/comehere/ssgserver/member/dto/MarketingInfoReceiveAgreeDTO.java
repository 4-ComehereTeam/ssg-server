package com.comehere.ssgserver.member.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class MarketingInfoReceiveAgreeDTO {

	private boolean isMarketingAgree;

	private boolean isEmailAgree;

	private boolean isSmsAgree;

	private boolean isMailAgree;

	private boolean isCallAgree;

	private boolean isSimpleMember;
}
