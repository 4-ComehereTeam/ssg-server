package com.comehere.ssgserver.member.domain;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;

@Entity
public class Agree {

	@Id
	@GeneratedValue(strategy = jakarta.persistence.GenerationType.IDENTITY)
	private Long id;

	private String email;

	@Column(columnDefinition = "TINYINT", length = 1, nullable = true)
	private short isTermssAgree;

	@Column(columnDefinition = "TINYINT", length = 1, nullable = true)
	private short isMarketingAgree;

	@Column(columnDefinition = "TINYINT", length = 1, nullable = true)
	private short isSmsAgree;

	@Column(columnDefinition = "TINYINT", length = 1, nullable = true)
	private short isMailAgree;

	@Column(columnDefinition = "TINYINT", length = 1, nullable = true)
	private short isCallAgree;

	@Column(columnDefinition = "TINYINT", length = 1, nullable = true)
	private short isSimpleAgree;

}
