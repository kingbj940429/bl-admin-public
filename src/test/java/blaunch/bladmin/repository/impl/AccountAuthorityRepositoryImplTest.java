package blaunch.bladmin.repository.impl;

import blaunch.bladmin.entity.Account;
import blaunch.bladmin.entity.AccountAuthority;
import blaunch.bladmin.entity.Authority;
import blaunch.bladmin.exception.ErrorCode;
import blaunch.bladmin.exception.custom.AuthorityNotFoundException;
import blaunch.bladmin.repository.AccountAuthorityRepository;
import blaunch.bladmin.repository.AccountRepository;
import blaunch.bladmin.repository.AuthorityRepository;
import blaunch.bladmin.repository.custom.AccountAuthorityRepositoryCustom;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Transactional(readOnly = true)
class AccountAuthorityRepositoryImplTest {
    @Autowired AccountAuthorityRepositoryCustom accountAuthorityRepositoryImpl;
    @Autowired AccountRepository accountRepository;
    @Autowired AuthorityRepository authorityRepository;
    @Autowired AccountAuthorityRepository accountAuthorityRepository;
    @Autowired EntityManager em;

    @Test
    @DisplayName("회원 조회")
    void findOneByAccountId(){
        //given
        AccountAuthority accountAuthority = accountAuthorityRepository.findFirstByOrderByAccountAsc().orElseThrow(RuntimeException::new);
        String id = accountAuthority.getAccount().getId();
        //when
        AccountAuthority findAccountAuthority = accountAuthorityRepositoryImpl.findOneByAccountId(id).orElseThrow(RuntimeException::new);

        //then
    }

    @Test
    @Transactional
    @DisplayName("권한 업데이트 하기")
    void updateAuthorityName(){
        //given
        String authorityName = "ROLE_ADMIN";

        Optional<Account> firstAccount = accountRepository.findOneByUserId("kingbj");
        AccountAuthority accountAuthority = accountAuthorityRepositoryImpl.findOneByAccountId(firstAccount.get().getId()).orElseThrow(RuntimeException::new);
        Authority findAuthorityName = authorityRepository.findOneByAuthorityName(authorityName).orElseThrow(() -> new AuthorityNotFoundException(ErrorCode.ROLE_NOT_FOUND));

        //when
        System.out.println("accountAuthority = " + accountAuthority.getAuthority().getAuthorityName());
        accountAuthority.updateAuthority(findAuthorityName);
        System.out.println("accountAuthority = " + accountAuthority.getAuthority().getAuthorityName());

        em.flush();
        em.clear();
        //then

        AccountAuthority findAccountAuthority2 = accountAuthorityRepositoryImpl.findOneByAccountId(firstAccount.get().getId()).orElseThrow(RuntimeException::new);
        assertThat(findAccountAuthority2.getAuthority().getAuthorityName()).isEqualTo(authorityName);
    }

}