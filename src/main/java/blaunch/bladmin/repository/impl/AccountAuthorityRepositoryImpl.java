package blaunch.bladmin.repository.impl;

import blaunch.bladmin.entity.AccountAuthority;
import blaunch.bladmin.entity.QAccountAuthority;
import blaunch.bladmin.entity.QAuthority;
import blaunch.bladmin.repository.custom.AccountAuthorityRepositoryCustom;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

import static blaunch.bladmin.entity.QAccountAuthority.*;
import static blaunch.bladmin.entity.QAuthority.*;

@Component
@RequiredArgsConstructor
public class AccountAuthorityRepositoryImpl implements AccountAuthorityRepositoryCustom {
    private final JPAQueryFactory queryFactory;

    @Override
    public Optional<AccountAuthority> findOneByAccountId(String accountId) {
        AccountAuthority accountAuthority = queryFactory
                .selectFrom(QAccountAuthority.accountAuthority)
                .join(QAccountAuthority.accountAuthority.authority, authority).fetchJoin()
                .where(
                        QAccountAuthority.accountAuthority.account.id.eq(accountId)
                )
                .fetchOne();

        return Optional.ofNullable(accountAuthority);
    }

    @Override
    public Optional<AccountAuthority> findOneByAuthorityName(String authorityName) {
        AccountAuthority accountAuthority = queryFactory
                .selectFrom(QAccountAuthority.accountAuthority)
                .join(QAccountAuthority.accountAuthority.authority, authority).fetchJoin()
                .where(
                        QAccountAuthority.accountAuthority.authority.authorityName.eq(authorityName)
                )
                .fetchOne();

        return Optional.ofNullable(accountAuthority);
    }
}
