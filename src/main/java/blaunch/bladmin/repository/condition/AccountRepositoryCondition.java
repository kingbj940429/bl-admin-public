package blaunch.bladmin.repository.condition;

import blaunch.bladmin.entity.status.PlatformStatus;
import blaunch.bladmin.entity.status.UserStatus;
import com.querydsl.core.BooleanBuilder;
import com.querydsl.core.types.Order;
import com.querydsl.core.types.OrderSpecifier;
import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.core.types.dsl.PathBuilder;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.util.StringUtils;

import static blaunch.bladmin.entity.QAccount.account;

public class AccountRepositoryCondition {

    public static BooleanExpression userStatusEq(UserStatus userStatus) {
        return StringUtils.hasText(String.valueOf(userStatus)) && userStatus != null ? account.userStatus.eq(userStatus) : null;
    }

    public static BooleanExpression userIdEq(String userId) {
        return StringUtils.hasText(userId) ? account.userId.eq(userId) : null;
    }

    public static BooleanExpression platformStatusEq(PlatformStatus platformStatus) {
        return StringUtils.hasText(String.valueOf(platformStatus)) ? account.platformStatus.eq(platformStatus) : null;
    }
}
