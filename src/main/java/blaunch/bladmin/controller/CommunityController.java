package blaunch.bladmin.controller;

import blaunch.bladmin.dto.CommunityDto;
import blaunch.bladmin.dto.ResponseDto;
import blaunch.bladmin.dto.condition.CommunityCondition;
import blaunch.bladmin.entity.Community.Community;
import blaunch.bladmin.exception.ErrorCode;
import blaunch.bladmin.exception.custom.CommunityNotFoundException;
import blaunch.bladmin.repository.CommunityRepository;
import blaunch.bladmin.service.CommunityService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static java.util.stream.Collectors.toList;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1")
public class CommunityController {
    private final CommunityRepository communityRepository;
    private final CommunityService communityService;

    @GetMapping("/communities")
    public Page<CommunityDto.FindCommunitiesRes> findCommunities(@PageableDefault(page = 0, size = 10) Pageable pageable){
        CommunityCondition communityCondition = CommunityCondition.builder().build();
        Page<Community> communities = communityRepository.findCommunities(communityCondition, pageable);

        List<CommunityDto.FindCommunitiesRes> findCommunitiesRes = communities.stream()
                .map(community -> new CommunityDto.FindCommunitiesRes(community))
                .collect(toList());

        return new PageImpl(findCommunitiesRes, pageable, communities.getTotalElements());
    }

    @GetMapping("/communities/{communityId}")
    public ResponseDto.Single<CommunityDto.FindCommunityRes> findCommunity(@PathVariable("communityId") String id){
        CommunityCondition build = CommunityCondition.builder().id(id).build();
        CommunityDto.FindCommunityRes findCommunityRes = communityRepository.findCommunity(build).orElseThrow(() -> new CommunityNotFoundException(ErrorCode.COMMUNITY_NOT_FOUND));

        return new ResponseDto.Single(findCommunityRes);
    }

    @PostMapping("/communities")
    public ResponseDto.Single<Community> create(@RequestBody CommunityDto.InsCommunity insCommunity){
        CommunityDto.InsCommunityRes insCommunityRes = new CommunityDto.InsCommunityRes(communityService.create(insCommunity));
        return new ResponseDto.Single(insCommunityRes);
    }

    @PutMapping("/communities/{communityId}/active-status")
    public ResponseDto.Single<CommunityDto.UpdActiveStatus> updActiveStatus(@RequestBody CommunityDto.UpdActiveStatus updActiveStatusDto,
                                                                            @PathVariable("communityId") String id){

        return new ResponseDto.Single(communityService.updActiveStatus(id, updActiveStatusDto));
    }

    @PutMapping("/communities/{communityId}")
    public ResponseDto.Single<CommunityDto.UpdCommunity> updCommunity(@RequestBody CommunityDto.UpdCommunity updCommunity,
                                                                      @PathVariable("communityId") String id){
        return new ResponseDto.Single<>(communityService.updCommunity(id, updCommunity));
    }

    @DeleteMapping("/communities/{communityId}")
    public ResponseDto.Single<String> delete(@PathVariable("communityId") String id){
        return new ResponseDto.Single(communityService.delete(id));
    }
}
