package blaunch.bladmin.service;

import blaunch.bladmin.dto.AccountDto;
import blaunch.bladmin.entity.Account;
import blaunch.bladmin.entity.status.UserStatus;
import blaunch.bladmin.exception.ErrorCode;
import blaunch.bladmin.exception.custom.AccountNotValidException;
import blaunch.bladmin.repository.AccountRepository;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import java.util.Optional;

import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Transactional(readOnly = true)
class AccountServiceTest {
    @Autowired AccountService accountService;
    @Autowired AccountRepository accountRepository;
    @Autowired EntityManager em;


    @Test
    @Transactional
    void create(){
        //given
        String userId = "testtesttest";
        AccountDto.insAccount insAccount = AccountDto.insAccount.builder().userId(userId).userPassword("1234").userStatus(UserStatus.R).build();

        //when
        accountService.create(insAccount);

        //then
        Optional<Account> findAccount = accountRepository.findOneByUserId(userId);
        assertThat(findAccount.get().getUserId()).isEqualTo(userId);
    }

    @Test
    @Transactional
    void updUserStatus() {
        //given
        UserStatus userStatus = UserStatus.O;
        Account findAccount = accountRepository.findFirstByOrderByCreatedDateDesc().orElseThrow(() -> new AccountNotValidException(ErrorCode.ACCOUNT_NOT_VALID));

        //when
        findAccount.updUserStatus(userStatus);

        em.flush();
        em.clear();
        //then
        Account result = accountRepository.getById(findAccount.getId());

        assertThat(result.getUserStatus()).isEqualTo(userStatus);
    }

}