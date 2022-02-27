package blaunch.bladmin.repository.custom;

import blaunch.bladmin.dto.CommunityDto;
import blaunch.bladmin.dto.condition.CommunityCondition;
import blaunch.bladmin.entity.Community.Community;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

public interface CommunityRepositoryCustom {
    Page<Community> findCommunities(CommunityCondition condition, Pageable pageable);
    Optional<CommunityDto.FindCommunityRes> findCommunity(CommunityCondition condition);
}
