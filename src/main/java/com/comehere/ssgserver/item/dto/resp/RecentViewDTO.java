package com.comehere.ssgserver.item.dto.resp;

import java.time.LocalDateTime;

import com.comehere.ssgserver.item.domain.RecentViewItem;

import lombok.Getter;

@Getter
public class RecentViewDTO {
	private Long recentId;

	private Long itemId;

	public RecentViewDTO(RecentViewItem items) {
		this.recentId = items.getId();
		this.itemId = items.getItemId();
	}
}
