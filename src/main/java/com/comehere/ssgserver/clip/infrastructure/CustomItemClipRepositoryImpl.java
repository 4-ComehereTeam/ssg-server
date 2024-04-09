package com.comehere.ssgserver.clip.infrastructure;

import static com.comehere.ssgserver.clip.domain.QItemClip.*;

import java.util.List;
import java.util.UUID;

import com.querydsl.jpa.impl.JPAQueryFactory;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class CustomItemClipRepositoryImpl implements CustomItemClipRepository {
	private final JPAQueryFactory query;

	@Override
	public void deleteItemClip(UUID uuid, Long itemId) {
		query.delete(itemClip)
				.where(
						itemClip.uuid.eq(uuid),
						itemClip.itemId.eq(itemId)
				)
				.execute();
	}

	@Override
	public List<Long> findItemIdsByUuid(UUID uuid) {
		return query.select(itemClip.itemId)
				.from(itemClip)
				.where(itemClip.uuid.eq(uuid))
				.fetch();
	}
}
