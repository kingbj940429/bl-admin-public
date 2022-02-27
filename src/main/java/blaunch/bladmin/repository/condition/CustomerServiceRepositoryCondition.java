package blaunch.bladmin.repository.condition;

import blaunch.bladmin.entity.status.CustomerServiceKind;
import com.querydsl.core.types.dsl.BooleanExpression;
import org.springframework.util.StringUtils;

import static blaunch.bladmin.entity.QCustomerService.customerService;

public class CustomerServiceRepositoryCondition {
    public static BooleanExpression idEq(String id){
        return StringUtils.hasText(id) ? customerService.id.eq(id) : null;
    }

    public static BooleanExpression csKindEq(CustomerServiceKind csKind){
        return csKind != null ? customerService.csKind.eq(csKind) : null;
    }
}
