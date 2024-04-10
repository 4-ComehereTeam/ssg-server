package com.comehere.ssgserver.member.application;

import java.util.UUID;

import com.comehere.ssgserver.member.dto.req.ModifyEmailReqDTO;
import com.comehere.ssgserver.member.dto.req.ModifyPhoneReqDTO;
import com.comehere.ssgserver.member.dto.req.ModifyPwdReqDTO;

public interface MemberService {
	Boolean modifyPassword(UUID uuid, ModifyPwdReqDTO modifyPwdReqDTO);

	Boolean modifyEmail(UUID uuid, ModifyEmailReqDTO modifyEmailReqDTO);

	Boolean modifyPhone(UUID uuid, ModifyPhoneReqDTO modifyPhoneReqDTO);

	Boolean resignMember(UUID uuid);
}
