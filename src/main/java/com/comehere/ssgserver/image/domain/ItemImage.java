package com.comehere.ssgserver.image.domain;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Getter;

@Entity
@Getter
public class ItemImage {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	private Long itemId;

	private String url; // imageUrl

	private String alt;

	@Column(columnDefinition = "TINYINT")
	// private Boolean isThumbnail; // short? -> 0,1 제외 다른 숫자 들어올 여지 있음.. boolean?
	private Short isThumbnail; // is 사용 안하는게 좋음..
}
