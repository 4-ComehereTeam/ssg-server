package com.comehere.ssgserver.clip.domain;

import java.util.UUID;

import com.comehere.ssgserver.item.domain.Item;
import com.comehere.ssgserver.member.domain.Member;

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
public class ItemClip {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	private UUID uuid;

	private Long itemId;

	@Builder
	public ItemClip(Long id, UUID uuid, Long itemId) {
		this.id = id;
		this.uuid = uuid;
		this.itemId = itemId;
	}
}
