package com.comehere.ssgserver.member.dto;

import java.time.LocalDateTime;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
@AllArgsConstructor
public class DormantMemberDTO {

	private Long id;
	// 회원의 마지막 활동 시간
	private LocalDateTime updateAt;

}
