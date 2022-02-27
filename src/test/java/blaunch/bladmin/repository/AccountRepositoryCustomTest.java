package blaunch.bladmin.repository;

import blaunch.bladmin.dto.AccountDto;
import blaunch.bladmin.dto.condition.AccountCondition;
import blaunch.bladmin.entity.Account;
import blaunch.bladmin.entity.status.UserStatus;
import blaunch.bladmin.exception.ErrorCode;
import blaunch.bladmin.exception.custom.AccountNotValidException;
import blaunch.bladmin.repository.custom.AccountRepositoryCustom;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.transaction.annotation.Transactional;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
@Transactional(readOnly = true)
class AccountRepositoryCustomTest {
    @Autowired AccountRepositoryCustom accountRepositoryImpl;
    @Autowired AccountRepository accountRepository;

    @Test
    @DisplayName("사용자 관리 - 사용자 리스트 가져오기")
    void findByAccountsPage(){
        //given
        PageRequest pageRequest = PageRequest.of(0, 10);

        //when
        Page<AccountDto.AccountManageRes> accounts = accountRepositoryImpl.findByAccountsPage(AccountCondition.builder().userStatus(UserStatus.R).build(), pageRequest);

        //then
        System.out.println("accounts.getTotalElements() = " + accounts.getTotalElements());
    }

    @Test
    @DisplayName("사용자 관리 > 사용자 정보 > 상세 정보")
    void findById(){
        //given
        Account account = accountRepository.findFirstByOrderByCreatedDateDesc().orElseThrow(() -> new AccountNotValidException(ErrorCode.ACCOUNT_NOT_VALID));
        AccountDto.FindAccountRes findAccountRes = accountRepository.findById(account.getId())
                .map(account1 -> new AccountDto.FindAccountRes(account1))
                .orElseThrow(() -> new AccountNotValidException(ErrorCode.ACCOUNT_NOT_VALID));

        //when

        //then
        assertThat(findAccountRes).isNotNull();
    }
}