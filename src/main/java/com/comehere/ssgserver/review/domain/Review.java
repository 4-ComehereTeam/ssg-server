package com.comehere.ssgserver.review.domain;

import java.util.UUID;

import com.comehere.ssgserver.common.entity.BaseEntity;
import com.comehere.ssgserver.member.domain.Member;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@NoArgsConstructor
public class Review extends BaseEntity {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(nullable = false, updatable = false)
	private UUID uuid;

	@Column(nullable = false)
	private String signinId;

	@Column(columnDefinition = "TINYINT", length = 1, nullable = false)
	private Short star;

	@Column(columnDefinition = "LONGTEXT", nullable = false)
	private String content;

	@Column(nullable = false, updatable = false)
	private Long purchaseListId;

	@Column(nullable = false, updatable = false)
	private String itemCode;

	@Builder

	public Review(Long id, UUID uuid, String signinId, Short star, String content, Long purchaseListId,
			String itemCode) {
		this.id = id;
		this.uuid = uuid;
		this.signinId = signinId;
		this.star = star;
		this.content = content;
		this.purchaseListId = purchaseListId;
		this.itemCode = itemCode;
	}
}
