package blaunch.bladmin.service;

import blaunch.bladmin.dto.CommunityDto;
import blaunch.bladmin.entity.Account;
import blaunch.bladmin.entity.Community.Community;
import blaunch.bladmin.entity.status.ActiveStatus;
import blaunch.bladmin.entity.status.UserStatus;
import blaunch.bladmin.exception.ErrorCode;
import blaunch.bladmin.exception.custom.CommunityNotFoundException;
import blaunch.bladmin.repository.CommunityRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
@Transactional(readOnly = true)
class CommunityServiceTest {
    @Autowired CommunityRepository communityRepository;
    @Autowired EntityManager em;

    @BeforeEach
    @Transactional
    void before(){
        Account account = Account.builder().userId("test").userStatus(UserStatus.S).nickname("닉네임").userName("유저 이름").build();
        em.persist(account);

        Community community = Community.builder().account(account).activeStatus(ActiveStatus.ENABLE).contents("테스트-컨텐츠").title("테스트-제목").build();
        em.persist(community);
        em.flush();
        em.clear();
    }

    @Test
    @DisplayName("커뮤니티 게시글 활성화 업데이트")
    @Transactional
    void updActiveStatus(){
        //given
        Community findCommunity = communityRepository.findByContentsAndTitle("테스트-컨텐츠", "테스트-제목");
        CommunityDto.UpdActiveStatus dto = CommunityDto.UpdActiveStatus.builder().activeStatus(ActiveStatus.DISENABLE).build();

        //when
        Community community = communityRepository.findById(findCommunity.getId()).orElseThrow(() -> new CommunityNotFoundException(ErrorCode.COMMUNITY_NOT_FOUND));
        findCommunity.updActiveStatus(dto.getActiveStatus());

        em.flush();
        em.clear();
        //then
        Community result = communityRepository.findById(findCommunity.getId()).orElseThrow(() -> new CommunityNotFoundException(ErrorCode.COMMUNITY_NOT_FOUND));
        assertThat(result.getId()).isEqualTo(community.getId());
        assertThat(result.getActiveStatus()).isEqualTo(ActiveStatus.DISENABLE);
    }

    @Test
    @DisplayName("커뮤니티 게시글 관리자용 업데이트")
    @Transactional
    void updCommunity(){
        //given
        String category = "0001";
        String tag = "테스트-태그-1";
        String contents = "테스트-컨텐츠-1";
        String title = "테스트-타이틀-1";

        Community findCommunity = communityRepository.findByContentsAndTitle("테스트-컨텐츠", "테스트-제목");
        CommunityDto.UpdCommunity dto = CommunityDto.UpdCommunity.builder().category(category).tag(tag).contents(contents).title(title).build();
        //when
        findCommunity.updCommunity(dto.getCategory(), dto.getTitle(), dto.getContents(), dto.getTag());

        em.flush();
        em.clear();

        //then
        Community updatedCommunity = communityRepository.findByContentsAndTitle(contents, title);
        assertThat(updatedCommunity.getCategory()).isEqualTo(category);
        assertThat(updatedCommunity.getTag()).isEqualTo(tag);
        assertThat(updatedCommunity.getContents()).isEqualTo(contents);
        assertThat(updatedCommunity.getTitle()).isEqualTo(title);
    }
}