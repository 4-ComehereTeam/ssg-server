package com.comehere.ssgserver.item.dto.req;

import java.util.List;

import lombok.Getter;

@Getter
public class DeleteRecentViewReqDTO {
	List<Long> recentIds;
}
