package blaunch.bladmin.controller;

import blaunch.bladmin.dto.AccountDto;
import blaunch.bladmin.dto.ResponseDto;
import blaunch.bladmin.dto.condition.AccountCondition;
import blaunch.bladmin.entity.Account;
import blaunch.bladmin.entity.status.UserStatus;
import blaunch.bladmin.exception.ErrorCode;
import blaunch.bladmin.exception.custom.AccountNotValidException;
import blaunch.bladmin.repository.AccountRepository;
import blaunch.bladmin.service.AccountService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1")
public class AccountController {

    private final AccountRepository accountRepository;
    private final AccountService accountService;

    /**
     * 비런치 관리자 회원가입
     * @param accountDto
     * @return
     */
    @PostMapping("/signup")
    public AccountDto.Res signUp(@Valid @RequestBody AccountDto.insAccount accountDto) {
        return new AccountDto.Res(accountService.create(accountDto));
    }

    /**
     * 사용자 관리 > 사용자 리스트
     * @param pageable
     * @return
     */
    //members?page=0&size=3&sort=id,desc&sort=username,desc
    //@PreAuthorize("hasRole('ADMIN')")
    @GetMapping("/accounts")
    public Page<AccountDto.AccountManageRes> findAccounts(@PageableDefault(page = 0, size = 10) Pageable pageable,
                                                          @RequestParam(value = "userstatus", required = false) UserStatus userStatus) {

        return accountRepository.findByAccountsPage(AccountCondition.builder().userStatus(userStatus).build(), pageable);
    }

    /**
     * 사용자 관리 > 사용자 정보 > 상세 정보
     * @param accountId
     * @return
     */
    @GetMapping("/accounts/{accountId}")
    public ResponseDto.Single<AccountDto.FindAccountRes> findAccount(@PathVariable String accountId) {
        AccountDto.FindAccountRes findAccountRes = accountRepository.findById(accountId)
                .map(account -> new AccountDto.FindAccountRes(account))
                .orElseThrow(() -> new AccountNotValidException(ErrorCode.ACCOUNT_NOT_VALID));

        return new ResponseDto.Single(findAccountRes);
    }

    /**
     * 사용자 관리 > 사용자 정보 > 활성화/비활성화
     * @param accountId
     * @param dto
     * @return
     */
    @PutMapping("/accounts/{accountId}/userstatus")
    public ResponseDto.Single<AccountDto.updAccountUserStatus> updUserStatus(@PathVariable String accountId,
                                                                             @RequestBody AccountDto.updAccountUserStatus dto) {
        Account account = accountService.updUserStatus(accountId, dto);

        return new ResponseDto.Single(account);
    }

}
