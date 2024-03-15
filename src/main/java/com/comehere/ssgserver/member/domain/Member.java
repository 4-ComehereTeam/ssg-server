package com.comehere.ssgserver.member.domain;

import java.time.LocalDateTime;
import java.util.List;

import com.comehere.ssgserver.purchase.domain.Address;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@NoArgsConstructor
public class Member {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@OneToMany(mappedBy = "member")
	private List<Address> addressList;

	private String name;

	private String birthday;

	@Column(columnDefinition = "TINYINT", length = 1, nullable = true)
	private Short gender;

	private String phoneNumber;

	private String loginId;

	private String password;

	private String email;

	@Column(columnDefinition = "DATETIME")
	private LocalDateTime signupTime;

	@Column(columnDefinition = "TINYINT", length = 1, nullable = true)
	private Short isUniverseClub;

	@Column(columnDefinition = "DATETIME")
	private LocalDateTime resignTime;

	private int resignCount;

	private short status;
}
