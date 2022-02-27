package blaunch.bladmin.repository;

import blaunch.bladmin.dto.condition.CustomerServiceCondition;
import blaunch.bladmin.entity.Account;
import blaunch.bladmin.entity.CsAnswer;
import blaunch.bladmin.entity.CustomerService;
import blaunch.bladmin.entity.status.CustomerServiceKind;
import blaunch.bladmin.entity.status.UserStatus;
import blaunch.bladmin.exception.ErrorCode;
import blaunch.bladmin.exception.custom.CustomerServiceNotFoundException;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;

@SpringBootTest
@Transactional(readOnly = true)
class CsAnswerRepositoryTest {
    @Autowired EntityManager em;
    @Autowired CsAnswerRepository csAnswerRepository;
    @Autowired CustomerServiceRepository customerServiceRepository;

    @BeforeEach
    @Transactional
    void before(){
        Account account = Account.builder().userId("test").userStatus(UserStatus.S).nickname("닉네임").userName("유저 이름").build();
        em.persist(account);

        CustomerService cs = CustomerService.builder().account(account).csTitle("csTitle").csContents("csContents").csKind(CustomerServiceKind.I).csType("0001").build();
        em.persist(cs);

        CsAnswer csAnswer = CsAnswer.builder().account(account).cs(cs).content("contents").build();
        em.persist(csAnswer);

        em.flush();
        em.clear();
    }

    @Test
    @Transactional
    @DisplayName("문의하기 답변 조회")
    void findCsAnswer(){
        //given
        CustomerService findCs = customerServiceRepository.findFirstByOrderByCreatedDateDesc().orElseThrow(() -> new CustomerServiceNotFoundException(ErrorCode.CS_NOT_FOUND));

        //then
        CsAnswer csAnswer = csAnswerRepository.findByCsId(findCs.getId());

        //when
        Assertions.assertThat(csAnswer.getCs().getId()).isEqualTo(findCs.getId());
    }

}