package com.comehere.ssgserver.item.vo.resp;

import java.util.List;

import lombok.Getter;

@Getter
public class RecentViewListRespVO {
	private List<RecentViewVO> recentItems;

	private Integer currentPage;

	private Boolean hasNext;
}
