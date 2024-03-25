package com.comehere.ssgserver.review.domain;

import java.time.LocalDateTime;

import com.comehere.ssgserver.member.domain.Member;
import com.comehere.ssgserver.review.vo.ReviewUpdateVo;

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
public class Review {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "member_id")
	private Member member;

	@Column(columnDefinition = "TINYINT", length = 1, nullable = false)
	private Short star;

	@Column(columnDefinition = "TINYINT", length = 1, nullable = true)
	private Short taste;

	@Column(columnDefinition = "TINYINT", length = 1, nullable = true)
	private Short boxing;

	@Column(columnDefinition = "TINYINT", length = 1, nullable = true)
	private Short life;

	@Column(columnDefinition = "LONGTEXT")
	private String content;

	@Column(nullable = false)
	private Long purchaseListId;

	private LocalDateTime date;

	@Column(nullable = false)
	private String itemCode;

	@Builder
	public Review(Member member, Long purchaseListId, Short star, Short taste, Short boxing, Short life, String content,
			LocalDateTime date,
			String itemCode) {
		this.member = member;
		this.purchaseListId = purchaseListId;
		this.itemCode = itemCode;
		this.star = star;
		this.taste = taste;
		this.boxing = boxing;
		this.life = life;
		this.content = content;
		this.date = date;
	}

	public void updateReview(ReviewUpdateVo reviewUpdateVo) {
		this.star = reviewUpdateVo.getStar();
		this.taste = reviewUpdateVo.getTaste();
		this.boxing = reviewUpdateVo.getBoxing();
		this.life = reviewUpdateVo.getLife();
		this.content = reviewUpdateVo.getContent();
	}
}
