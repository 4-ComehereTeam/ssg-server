package com.comehere.ssgserver.item.domain;

import java.time.LocalDateTime;
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
public class RecentViewItem {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(nullable = false, updatable = false)
	private UUID uuid;

	@Column(nullable = false, updatable = false, unique = true)
	private Long itemId;

	private LocalDateTime viewDate;

	@Builder
	public RecentViewItem(Long id, UUID uuid, Long itemId, LocalDateTime viewDate) {
		this.id = id;
		this.uuid = uuid;
		this.itemId = itemId;
		this.viewDate = viewDate;
	}
}
