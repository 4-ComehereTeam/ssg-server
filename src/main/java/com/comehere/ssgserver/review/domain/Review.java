package com.comehere.ssgserver.review.domain;

import java.time.LocalDateTime;

import com.comehere.ssgserver.member.domain.Member;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

@Entity
public class Review {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "member_id")
	private Member member;

	@Column(columnDefinition = "TINYINT", length = 1, nullable = true)
	private Short star;

	@Column(columnDefinition = "TINYINT", length = 1, nullable = true)
	private Short taste;

	@Column(columnDefinition = "TINYINT", length = 1, nullable = true)
	private Short boxing;

	@Column(columnDefinition = "TINYINT", length = 1, nullable = true)
	private Short life;

	@Column(columnDefinition = "LONGTEXT")
	private String content;

	private LocalDateTime date;

	private String ItemCode;

}
