package blaunch.bladmin.repository;

import blaunch.bladmin.dto.CommunityDto;
import blaunch.bladmin.dto.condition.CommunityCondition;
import blaunch.bladmin.entity.Account;
import blaunch.bladmin.entity.Community.Community;
import blaunch.bladmin.entity.status.ActiveStatus;
import blaunch.bladmin.entity.status.UserStatus;
import blaunch.bladmin.exception.ErrorCode;
import blaunch.bladmin.exception.custom.CommunityNotFoundException;
import blaunch.bladmin.repository.impl.CommunityRepositoryImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
@Transactional(readOnly = true)
class CommunityRepositoryTest {
    @Autowired EntityManager em;
    @Autowired CommunityRepository communityRepository;
    @Autowired CommunityRepositoryImpl communityRepositoryImpl;

    @BeforeEach
    @Transactional
    void before(){
        Account account = Account.builder().userId("test").userStatus(UserStatus.S).nickname("닉네임").userName("유저 이름").build();
        em.persist(account);

        for (int i = 0; i < 20; i++) {
            em.persist(Community.builder().account(account).activeStatus(ActiveStatus.ENABLE).contents("내용" + i).title("제목" + i).build());
        }
        em.flush();
        em.clear();
    }

    @Test
    @Transactional
    @DisplayName("커뮤니티 게시글 단일 조회")
    void findCommunities(){
        //given
        String userId = "test";
        String title = "제목1";

        //when
        Community findCommunity = communityRepository.findByTitle(title).orElseThrow(() -> new CommunityNotFoundException(ErrorCode.COMMUNITY_NOT_FOUND));

        //then
        assertThat(findCommunity.getAccount().getUserId()).isEqualTo(userId);
        assertThat(findCommunity.getActiveStatus()).isEqualTo(ActiveStatus.ENABLE);
        assertThat(findCommunity.getTitle()).isEqualTo(title);
    }

    @Test
    @Transactional
    @DisplayName("커뮤니티 게시글 페이징 조회")
    void findCommunitiesPage(){
        //given
        CommunityCondition communityCondition = CommunityCondition.builder().build();
        PageRequest pageRequest = PageRequest.of(0, 10);

        //when
        Page<Community> communities = communityRepositoryImpl.findCommunities(communityCondition, pageRequest);

        //then
        assertThat(communities.getContent().size()).isEqualTo(10);
    }

    @Test
    @Transactional
    void findCommunity(){
        //given
        Community findCommunity = communityRepository.findFirstByOrderByCreatedDateDesc().orElseThrow(() -> new CommunityNotFoundException(ErrorCode.COMMUNITY_NOT_FOUND));

        //when
        CommunityCondition build = CommunityCondition.builder().id(findCommunity.getId()).build();
        Optional<CommunityDto.FindCommunityRes> community = communityRepositoryImpl.findCommunity(build);
        CommunityDto.FindCommunityRes findCommunityRes = community.get();

        //then
        assertThat(findCommunityRes.getTitle()).isEqualTo(findCommunity.getTitle());
        assertThat(findCommunityRes.getActiveStatus()).isEqualTo(findCommunity.getActiveStatus());
    }
}