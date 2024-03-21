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
	private Short isTermssAgree;

	@Column(columnDefinition = "TINYINT", length = 1, nullable = true)
	private Short isMarketingAgree;

	@Column(columnDefinition = "TINYINT", length = 1, nullable = true)
	private Short isSmsAgree;

	@Column(columnDefinition = "TINYINT", length = 1, nullable = true)
	private Short isMailAgree;

	@Column(columnDefinition = "TINYINT", length = 1, nullable = true)
	private Short isCallAgree;

	@Column(columnDefinition = "TINYINT", length = 1, nullable = true)
	private Short isSimpleAgree;

}
