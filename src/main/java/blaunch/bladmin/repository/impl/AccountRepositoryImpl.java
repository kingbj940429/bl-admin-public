package blaunch.bladmin.repository.impl;

import blaunch.bladmin.dto.AccountDto;
import blaunch.bladmin.dto.QAccountDto_FindAccountsRes;
import blaunch.bladmin.dto.condition.AccountCondition;
import blaunch.bladmin.repository.custom.AccountRepositoryCustom;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;

import java.util.List;

import static blaunch.bladmin.entity.QAccount.account;
import static blaunch.bladmin.repository.condition.AccountRepositoryCondition.*;
import static java.util.stream.Collectors.*;

@Component
@RequiredArgsConstructor
public class AccountRepositoryImpl implements AccountRepositoryCustom {

    private final JPAQueryFactory queryFactory;

    @Override
    public List<AccountDto.FindAccountsRes> findAccounts(AccountCondition condition, Pageable pageable) {
        return queryFactory
                .select(new QAccountDto_FindAccountsRes(
                        account
                ))
                .from(account)
                .where(
                        userStatusEq(condition.getUserStatus())
                )
                .offset(pageable.getOffset())
                .limit(pageable.getPageSize())
                .fetch();
    }

    @Override
    public Page<AccountDto.AccountManageRes> findByAccountsPage(AccountCondition accountCondition, Pageable pageable) {

        List<AccountDto.AccountManageRes> collect = queryFactory
                .selectFrom(account)
                .where(
                        userStatusEq(accountCondition.getUserStatus())
                )
                .offset(pageable.getOffset())
                .limit(pageable.getPageSize())
                .orderBy(account.createdDate.desc())
                .fetch()
                .stream()
                .map(findAccount -> new AccountDto.AccountManageRes(findAccount))
                .collect(toList());

        Long count = queryFactory
                .select(account.count())
                .from(account)
                .fetchOne();

        return new PageImpl<AccountDto.AccountManageRes>(collect, pageable, count);
    }


}
