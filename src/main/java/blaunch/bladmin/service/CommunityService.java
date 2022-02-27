package blaunch.bladmin.service;

import blaunch.bladmin.dto.CommunityDto;
import blaunch.bladmin.entity.Account;
import blaunch.bladmin.entity.Community.Community;
import blaunch.bladmin.entity.status.ActiveStatus;
import blaunch.bladmin.exception.ErrorCode;
import blaunch.bladmin.exception.custom.AccountNotValidException;
import blaunch.bladmin.exception.custom.CommunityNotFoundException;
import blaunch.bladmin.repository.AccountRepository;
import blaunch.bladmin.repository.CommunityRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class CommunityService {
    private final AccountRepository accountRepository;
    private final CommunityRepository communityRepository;

    @Transactional
    public Community create(CommunityDto.InsCommunity dto){
        Account findAccount = accountRepository.findById(dto.getAccount().getId()).orElseThrow(() -> new AccountNotValidException(ErrorCode.ACCOUNT_NOT_VALID));
        CommunityDto.InsCommunity insCommunity = CommunityDto.InsCommunity.builder()
                .title(dto.getTitle())
                .contents(dto.getContents())
                .category(dto.getCategory())
                .tag(dto.getTag())
                .activeStatus(ActiveStatus.ENABLE)
                .account(findAccount)
                .build();

        return communityRepository.save(insCommunity.toEntity());
    }

    @Transactional
    public CommunityDto.UpdActiveStatus updActiveStatus(String communityId, CommunityDto.UpdActiveStatus dto){
        Community findCommunity = communityRepository.findById(communityId).orElseThrow(() -> new CommunityNotFoundException(ErrorCode.COMMUNITY_NOT_FOUND));
        findCommunity.updActiveStatus(dto.getActiveStatus());

        return dto;
    }

    @Transactional
    public CommunityDto.UpdCommunity updCommunity(String communityId, CommunityDto.UpdCommunity dto){
        Community findCommunity = communityRepository.findById(communityId).orElseThrow(() -> new CommunityNotFoundException(ErrorCode.COMMUNITY_NOT_FOUND));
        findCommunity.updCommunity(dto.getCategory(), dto.getTitle(), dto.getContents(), dto.getTag());

        return dto;
    }

    @Transactional
    public String delete(String communityId){
        Community findCommunity = communityRepository.findById(communityId).orElseThrow(() -> new CommunityNotFoundException(ErrorCode.COMMUNITY_NOT_FOUND));
        communityRepository.delete(findCommunity);

        return communityId;
    }
}
