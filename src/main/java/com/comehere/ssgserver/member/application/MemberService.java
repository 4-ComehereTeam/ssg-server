package com.comehere.ssgserver.member.application;

import java.util.UUID;

import com.comehere.ssgserver.member.dto.ModifyEmailDTO;
import com.comehere.ssgserver.member.dto.ModifyPhoneDTO;
import com.comehere.ssgserver.member.dto.ModifyPwdDTO;

public interface MemberService {
	Boolean modifyPassword(UUID uuid, ModifyPwdDTO modifyPwdDTO);

	Boolean modifyEmail(UUID uuid, ModifyEmailDTO modifyEmailDTO);

	Boolean modifyPhone(UUID uuid, ModifyPhoneDTO modifyPhoneDTO);

	Boolean resignMember(UUID uuid);
}
