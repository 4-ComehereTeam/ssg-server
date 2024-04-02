package com.comehere.ssgserver.item.vo.req;

import java.sql.Array;
import java.util.ArrayList;
import java.util.List;

import lombok.Getter;
import lombok.ToString;

@Getter
public class DeleteRecentViewReqVO {
	List<Long> recentIds;
}
