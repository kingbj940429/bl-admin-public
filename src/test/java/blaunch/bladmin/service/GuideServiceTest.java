package blaunch.bladmin.service;

import blaunch.bladmin.dto.GuideDto;
import blaunch.bladmin.entity.Account;
import blaunch.bladmin.entity.guide.Guide;
import blaunch.bladmin.exception.ErrorCode;
import blaunch.bladmin.exception.custom.AccountNotValidException;
import blaunch.bladmin.exception.custom.GuideNotFoundException;
import blaunch.bladmin.repository.AccountRepository;
import blaunch.bladmin.repository.GuideRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
@Transactional(readOnly = true)
class GuideServiceTest {
    @Autowired EntityManager em;
    @Autowired GuideService guideService;
    @Autowired GuideRepository guideRepository;
    @Autowired AccountRepository accountRepository;

    @Test
    @DisplayName("비런치 가이드 등록")
    @Transactional
    void create(){
        //given
        Account account = accountRepository.findById("-1").orElseThrow(() -> new AccountNotValidException(ErrorCode.ACCOUNT_NOT_VALID));

        GuideDto.InsGuide insGuide = GuideDto.InsGuide.builder()
                .type("0001").title("타이틀").contents("내용").subTitle("서브타이틀").imgLink("이미지").account(account)
                .metaTitle("메타 타이틀").metaDesc("메타 내용").metaKeyword("메타 키워드")
                .ogTitle("오픈 그래픽 타이틀").ogDesc("오픈 그래픽 내용").ogImg("오픈 그래픽 이미지")
                .build();

        //when
        Guide guide = guideService.create(insGuide);
        Guide findGuide = guideRepository.findFirstByOrderByCreatedDateDesc().orElseThrow(() -> new GuideNotFoundException(ErrorCode.GUIDE_NOT_FOUND));

        //then
        assertThat(guide.getTitle()).isEqualTo(findGuide.getTitle());
        assertThat(guide.getMetaTitle()).isEqualTo(findGuide.getMetaTitle());
    }
}