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

	private UUID uuid;

	private String signInId;

	private String name;

	private String password;

	private String birthday;

	@Column(columnDefinition = "TINYINT", length = 1, nullable = true)
	private Short gender;

	private String phone;

	private String email;

	@Column(columnDefinition = "DATETIME")
	private LocalDateTime resignTime;

	private Integer resignCount = 0;

	// 1 : 계정 활성화
	// -1 : 탈퇴상태
	// 0 : 휴면상태
	private Short status = 1;

	@Enumerated(EnumType.STRING)
	private Role role = Role.USER;

	@Builder
	public Member(UUID uuid, String signinId, String name, String password, String birthday, Short gender, String phone,
			String email, Role role) {
		this.uuid = uuid;
		this.signInId = signinId;
		this.name = name;
		this.password = password;
		this.birthday = birthday;
		this.gender = gender;
		this.phone = phone;
		this.email = email;
		this.role = role;
	}

	public UUID getUserUuid() {
		return uuid;
	}
}
