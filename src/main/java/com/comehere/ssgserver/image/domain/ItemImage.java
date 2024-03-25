package com.comehere.ssgserver.image.domain;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Getter;
import lombok.extern.apachecommons.CommonsLog;

@Entity
@Getter
public class ItemImage {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(nullable = false)
	private Long itemId;

	@Column(nullable = false)
	private String imageUrl;

	@Column(nullable = false)
	private String alt;

	@Column(columnDefinition = "TINYINT")
	private Boolean thumbnail;
}
