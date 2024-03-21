package com.comehere.ssgserver.member.domain;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@NoArgsConstructor
public class Member {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	private String loginId;

	private String name;

	private String password;

	private String birthday;

	@Setter
	private String role;

	//@Column(columnDefinition = "TINYINT", length = 1, nullable = true)
	private Short gender;
	//
	// private String phoneNumber;
	//

	//
	// private String email;
	//
	// @Column(columnDefinition = "DATETIME")
	// private LocalDateTime signupTime;

	// @Column(columnDefinition = "TINYINT", length = 1, nullable = true)
	// private Short isUniverseClub;
	//
	// @Column(columnDefinition = "DATETIME")
	// private LocalDateTime resignTime;
	//
	// private Integer resignCount;
	//
	// private Short status;

	// 	@Builder
	// 	public Member(String name, String birthday, Short gender, String phoneNumber, String loginId, String password,
	// 			String email, LocalDateTime signupTime) {
	// 		this.name = name;
	// 		this.birthday = birthday;
	// 		this.gender = gender;
	// 		this.phoneNumber = phoneNumber;
	// 		this.loginId = loginId;
	// 		this.password = password;
	// 		this.email = email;
	// 		this.signupTime = signupTime;
	// 	}
	@Builder
	public Member(String loginId, String password, String role) {
		this.loginId = loginId;
		this.password = password;
	}
}
