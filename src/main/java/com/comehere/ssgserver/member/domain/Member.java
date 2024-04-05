package com.comehere.ssgserver.member.domain;

import java.time.LocalDateTime;
import java.util.UUID;

import com.comehere.ssgserver.common.entity.BaseEntity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@NoArgsConstructor
public class Member extends BaseEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(updatable = false)
	private UUID uuid;

	@Column(updatable = false)
	private String signinId;

	@Column(nullable = false, updatable = false)
	private String name;

	private String password;

	@Column(columnDefinition = "TINYINT", length = 1, nullable = true, updatable = false)
	private Short gender;

	private String phone;

	@Column(nullable = false)
	private String email;

	@Column(columnDefinition = "DATETIME", updatable = false)
	private LocalDateTime resignTime;

	@Column(updatable = false)
	private Integer resignCount = 0;

	// 1 : 계정 활성화
	// -1 : 탈퇴상태
	// 0 : 휴면상태
	@Column(updatable = false)
	private Short status = 1;

	@Column(updatable = false)
	@Enumerated(EnumType.STRING)
	private Role role = Role.USER;

	@Builder
	public Member(Long id, UUID uuid, String signinId, String name, String password, Short gender,
			String phone,
			String email, Role role) {
		this.id = id;
		this.uuid = uuid;
		this.signinId = signinId;
		this.name = name;
		this.password = password;
		this.gender = gender;
		this.phone = phone;
		this.email = email;
		this.role = role;
	}

}
