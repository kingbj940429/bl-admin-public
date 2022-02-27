package blaunch.bladmin.service;

import blaunch.bladmin.dto.AuthenticateDto;
import blaunch.bladmin.entity.Account;
import blaunch.bladmin.exception.ErrorCode;
import blaunch.bladmin.exception.custom.AccountNotValidException;
import blaunch.bladmin.repository.AccountRepository;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.transaction.annotation.Transactional;

import javax.security.auth.login.AccountNotFoundException;

import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Transactional(readOnly = true)
class AuthServiceTest {
    @Autowired AuthService authService;
    @Autowired AccountRepository accountRepository;
    @Autowired PasswordEncoder passwordEncoder;

    private String userId = "kingbj";
    private String password = "1234";
    private String authorityName = "ROLE_ADMIN";

    @Test
    @DisplayName("인증")
    void authenticate() throws AccountNotFoundException {
        //given
        AuthenticateDto.InsAuthenticate insAuthenticate = AuthenticateDto.InsAuthenticate.builder().userId(userId).userPassword(password).authorityName(authorityName).build();
        authService.authorize(insAuthenticate);
        //then

        //when
    }

    @Test
    @DisplayName("인가")
    void authorize() {
        //given
        Account findAccount = accountRepository
                .findOneByUserId(userId)
                .orElseThrow(() -> new AccountNotValidException(ErrorCode.ACCOUNT_NOT_VALID));

        //when
        boolean equals = passwordEncoder.matches(password, findAccount.getUserPassword());

        //then
        assertThat(equals).isTrue();
    }
}