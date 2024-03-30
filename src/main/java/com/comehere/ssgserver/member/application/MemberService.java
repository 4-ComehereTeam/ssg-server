package com.comehere.ssgserver.member.application;

import java.util.UUID;

import com.comehere.ssgserver.common.response.BaseResponse;
import com.comehere.ssgserver.member.dto.FindSigninIdDTO;
import com.comehere.ssgserver.member.dto.ModifyEmailDTO;
import com.comehere.ssgserver.member.dto.ModifyPhoneDTO;
import com.comehere.ssgserver.member.dto.ModifyPwdDTO;

public interface MemberService {
	BaseResponse<?> modifyPassword(UUID uuid, ModifyPwdDTO modifyPwdDTO);

	BaseResponse<?> modifyEmail(UUID uuid, ModifyEmailDTO modifyEmailDTO);

	BaseResponse<?> modifyPhone(UUID uuid, ModifyPhoneDTO modifyPhoneDTO);

	FindSigninIdDTO findSigninId(UUID uuidByAuthorization);
}
