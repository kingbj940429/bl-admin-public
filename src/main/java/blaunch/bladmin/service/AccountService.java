package blaunch.bladmin.service;

import blaunch.bladmin.dto.AccountDto;
import blaunch.bladmin.entity.Account;
import blaunch.bladmin.entity.status.PlatformStatus;
import blaunch.bladmin.exception.ErrorCode;
import blaunch.bladmin.exception.custom.AccountNotValidException;
import blaunch.bladmin.exception.custom.UserIdDuplicationException;
import blaunch.bladmin.repository.AccountRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class AccountService {
    private final AccountRepository accountRepository;

    @Transactional
    public Account create(AccountDto.insAccount accountDto) {
        boolean present = accountRepository.findOneByUserIdAndPlatformStatus(accountDto.getUserId(), PlatformStatus.X)
                .isPresent();

        if (!present) return accountRepository.save(accountDto.toEntity());

        throw new UserIdDuplicationException(ErrorCode.USERID_DUPLICATION);
    }

    @Transactional
    public Account updUserStatus(String accountId, AccountDto.updAccountUserStatus accountDto){
        Account findAccount = accountRepository.findById(accountId).orElseThrow(() -> new AccountNotValidException(ErrorCode.ACCOUNT_NOT_VALID));
        findAccount.updUserStatus(accountDto.getUserStatus());

        return findAccount;
    }
}
