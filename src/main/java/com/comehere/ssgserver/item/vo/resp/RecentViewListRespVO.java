package com.comehere.ssgserver.item.vo.resp;

import java.util.List;

import lombok.Getter;

@Getter
public class RecentViewListRespVO {
	List<RecentViewVO> recentItems;

	private Boolean hasNext;
}
