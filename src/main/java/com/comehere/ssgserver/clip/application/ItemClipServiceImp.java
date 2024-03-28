package com.comehere.ssgserver.clip.application;

import java.util.List;

import org.springframework.stereotype.Service;

import com.comehere.ssgserver.clip.domain.ItemClip;
import com.comehere.ssgserver.clip.dto.ItemClipDto;
import com.comehere.ssgserver.clip.dto.ItemIdsDTO;
import com.comehere.ssgserver.clip.infrastructure.ItemClipRepository;
import com.comehere.ssgserver.item.infrastructual.ItemRepository;
import com.comehere.ssgserver.member.infrastructure.MemberRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ItemClipServiceImp implements ItemClipService {
	private final ItemClipRepository itemClipRepository;
	private final ItemRepository itemRepository;
	private final MemberRepository memberRepository;

	@Override
	public void createItemClip(ItemClipDto itemClipDto) {
		ItemClip itemClip = ItemClip.builder()
				.item(itemRepository.findById(itemClipDto.getItemId())
						.orElseThrow(() -> new IllegalArgumentException("존재하지 않는 아이템입니다.")))
				.member(memberRepository.findById(itemClipDto.getMemberId())
						.orElseThrow(() -> new IllegalArgumentException("존재하지 않는 멤버입니다.")))
				.build();

		itemClipRepository.save(itemClip);
	}

	@Override
	public void deleteItemClip(ItemClipDto itemClipDto) {
		itemClipRepository.findByMemberIdAndItemId(itemClipDto.getMemberId(), itemClipDto.getItemId())
				.ifPresentOrElse(itemClipRepository::delete, () -> {
					throw new IllegalArgumentException("존재하지 않는 아이템 클립입니다.");
				});
	}

	@Override
	public ItemIdsDTO getClipList(Long memberId) {
		List<ItemClip> itemClips = itemClipRepository.findByMemberId(memberId);

		if (itemClips.isEmpty()) {
			throw new IllegalArgumentException("해당 회원은 좋아요 상품이 없습니다.");
		}

		return new ItemIdsDTO(itemClips.stream()
				.map(ItemClip::getId)
				.toList());
	}
}
