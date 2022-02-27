package blaunch.bladmin.repository.impl;

import blaunch.bladmin.dto.condition.CustomerServiceCondition;
import blaunch.bladmin.entity.CustomerService;
import blaunch.bladmin.repository.condition.CustomerServiceRepositoryCondition;
import blaunch.bladmin.repository.custom.CustomerServiceRepositoryCustom;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;

import java.util.List;

import static blaunch.bladmin.entity.QAccount.account;
import static blaunch.bladmin.entity.QCustomerService.customerService;

@RequiredArgsConstructor
public class CustomerServiceRepositoryImpl implements CustomerServiceRepositoryCustom {
    private final JPAQueryFactory queryFactory;

    @Override
    public Page<CustomerService> findCss(CustomerServiceCondition condition, Pageable pageable) {
        List<CustomerService> customerServices = queryFactory
                .selectFrom(customerService)
                .where(
                        CustomerServiceRepositoryCondition.csKindEq(condition.getCsKind())
                )
                .innerJoin(customerService.account, account).fetchJoin()
                .offset(pageable.getOffset())
                .limit(pageable.getPageSize())
                .fetch();

        Long count = queryFactory
                .select(customerService.id.count())
                .from(customerService)
                .where(
                        CustomerServiceRepositoryCondition.csKindEq(condition.getCsKind())
                )
                .fetchOne();

        return new PageImpl(customerServices, pageable, count);
    }
}
