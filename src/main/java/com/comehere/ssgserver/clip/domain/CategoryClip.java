package com.comehere.ssgserver.clip.domain;

import java.util.UUID;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@NoArgsConstructor
public class CategoryClip {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(nullable = false, updatable = false)
	private UUID uuid;

	private Long bigCategoryId;

	private Long middleCategoryId;

	private Long smallCategoryId;

	@Builder
	public CategoryClip(Long id, UUID uuid, Long bigCategoryId, Long middleCategoryId, Long smallCategoryId) {
		this.id = id;
		this.uuid = uuid;
		this.bigCategoryId = bigCategoryId;
		this.middleCategoryId = middleCategoryId;
		this.smallCategoryId = smallCategoryId;
	}
}
