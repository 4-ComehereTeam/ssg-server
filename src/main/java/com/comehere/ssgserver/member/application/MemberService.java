package com.comehere.ssgserver.member.application;

import java.util.UUID;

import com.comehere.ssgserver.common.response.BaseResponse;
import com.comehere.ssgserver.member.dto.ModifyPwdDTO;

public interface MemberService {
	BaseResponse<?> modifyPassword(UUID uuid, ModifyPwdDTO modifyPwdDTO);
}
