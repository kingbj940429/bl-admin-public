package blaunch.bladmin.repository.custom;

import blaunch.bladmin.entity.AccountAuthority;

import java.util.List;
import java.util.Optional;

public interface AccountAuthorityRepositoryCustom {
    Optional<AccountAuthority> findOneByAccountId(String accountId);
    Optional<AccountAuthority> findOneByAuthorityName(String authorityName);
}
