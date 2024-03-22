package com.comehere.ssgserver.clip.infrastructure;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.comehere.ssgserver.clip.domain.ItemClip;

public interface ItemClipRepository extends JpaRepository<ItemClip, Long> {
	Optional<ItemClip> findByMemberIdAndItemId(Long memberId, Long itemId);

	List<ItemClip> findByMemberId(Long memberId);
}
