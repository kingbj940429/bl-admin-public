package blaunch.bladmin.repository;

import blaunch.bladmin.dto.CustomerServiceDto;
import blaunch.bladmin.dto.condition.CustomerServiceCondition;
import blaunch.bladmin.entity.Account;
import blaunch.bladmin.entity.CustomerService;
import blaunch.bladmin.entity.status.CustomerServiceKind;
import blaunch.bladmin.entity.status.UserStatus;
import blaunch.bladmin.exception.ErrorCode;
import blaunch.bladmin.exception.custom.CustomerServiceNotFoundException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
@Transactional(readOnly = true)
class CustomerServiceRepositoryTest {
    @Autowired EntityManager em;
    @Autowired private CustomerServiceRepository customerServiceRepository;

    @BeforeEach
    @Transactional
    void before(){
        Account account = Account.builder().userId("test").userStatus(UserStatus.S).nickname("닉네임").userName("유저 이름").build();
        em.persist(account);

        for (int i = 0; i < 20; i++) {
            em.persist(CustomerService.builder().account(account).csTitle("csTitle").csContents("csContents").csKind(CustomerServiceKind.I).csType("0001").build());
        }
        em.flush();
        em.clear();
    }

    @Test
    @Transactional
    @DisplayName("cs 게시글 페이징 조회")
    void findCs(){
        //given
        CustomerServiceCondition condition = CustomerServiceCondition.builder().csKind(CustomerServiceKind.I).build();
        PageRequest pageRequest = PageRequest.of(0, 10);

        //when
        Page<CustomerService> csPage = customerServiceRepository.findCss(condition, pageRequest);

        //then
        assertThat(csPage.getSize()).isEqualTo(10);
    }

    @Test
    @Transactional
    @DisplayName("cs 게시글 단일 조회")
    void findCsById(){
        //given
        CustomerService cs = customerServiceRepository.findFirstByOrderByCreatedDateDesc().orElseThrow(() -> new CustomerServiceNotFoundException(ErrorCode.CS_NOT_FOUND));

        //when
        CustomerService findCs = customerServiceRepository.findById(cs.getId()).orElseThrow(() -> new CustomerServiceNotFoundException(ErrorCode.CS_NOT_FOUND));

        //then
        assertThat(findCs).isEqualTo(cs);
    }
}