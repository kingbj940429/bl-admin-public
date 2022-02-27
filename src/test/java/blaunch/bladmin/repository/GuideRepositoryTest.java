package blaunch.bladmin.repository;

import blaunch.bladmin.dto.GuideDto;
import blaunch.bladmin.dto.condition.GuideCondition;
import blaunch.bladmin.entity.Account;
import blaunch.bladmin.entity.guide.Guide;
import blaunch.bladmin.entity.status.ActiveStatus;
import blaunch.bladmin.exception.ErrorCode;
import blaunch.bladmin.exception.custom.GuideNotFoundException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
@Transactional(readOnly = true)
class GuideRepositoryTest {
    @Autowired EntityManager em;
    @Autowired GuideRepository guideRepository;

    @BeforeEach
    void before(){
        Account account = Account.builder().id("-1").userName("관리자").nickname("관리자").build();

        for (int i = 0; i < 10; i++) {
            em.persist(Guide.builder().account(account).title("test-guide-" + i).activeStatus(ActiveStatus.ENABLE).contents("test-contents-" + i).build());
        }
        em.flush();
        em.clear();
    }


    @Test
    @Transactional
    @DisplayName("비런치 가이드 조회")
    void findGuides(){
        //given
        int size = 5;
        int page = 0;

        PageRequest pageRequest = PageRequest.of(page, size, Sort.Direction.DESC, "regtm");

        //when
        GuideCondition build = GuideCondition.builder().build();
        Page<GuideDto.FindGuidesRes> findGuidesRes = guideRepository.findGuides(build, pageRequest);

        //then
        assertThat(findGuidesRes.getSize()).isEqualTo(size);
    }

    @Test
    @Transactional
    @DisplayName("비런치 가이드 단일 조회")
    void findGuideById(){
        //given
        Guide guide = guideRepository.findFirstByOrderByCreatedDateDesc().orElseThrow(() -> new GuideNotFoundException(ErrorCode.GUIDE_NOT_FOUND));

        //when
        GuideCondition guideCondition = GuideCondition.builder().id(guide.getId()).build();
        GuideDto.FindGuideByIdRes findGuideByIdRes = guideRepository.findGuideById(guideCondition).orElseThrow(() -> new GuideNotFoundException(ErrorCode.GUIDE_NOT_FOUND));

        //then
        assertThat(findGuideByIdRes.getId()).isEqualTo(findGuideByIdRes.getId());
        assertThat(findGuideByIdRes.getTitle()).isEqualTo(findGuideByIdRes.getTitle());
        assertThat(findGuideByIdRes.getActiveStatus()).isEqualTo(findGuideByIdRes.getActiveStatus());
    }

}