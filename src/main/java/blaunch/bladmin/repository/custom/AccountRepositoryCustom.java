package blaunch.bladmin.repository.custom;

import blaunch.bladmin.dto.AccountDto;
import blaunch.bladmin.dto.condition.AccountCondition;
import blaunch.bladmin.entity.Account;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface AccountRepositoryCustom {
    List<AccountDto.FindAccountsRes> findAccounts(AccountCondition accountCondition, Pageable pageable);
    Page<AccountDto.AccountManageRes> findByAccountsPage(AccountCondition accountCondition, Pageable pageable);
}
