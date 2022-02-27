package blaunch.bladmin.repository.condition;

import blaunch.bladmin.entity.business.QBusiness;
import blaunch.bladmin.entity.status.SalesStatus;
import com.querydsl.core.types.dsl.BooleanExpression;
import org.springframework.util.StringUtils;

import static blaunch.bladmin.entity.business.QBusiness.*;


public class BusinessRepositoryCondition {

    public static BooleanExpression idEq(String id){
        return StringUtils.hasText(id) ? business.id.eq(id) : null;
    }

    public static BooleanExpression salesStatusEq(SalesStatus salesStatus){
        return StringUtils.hasText(String.valueOf(salesStatus)) && salesStatus != null ? business.salesStatus.eq(salesStatus) : null;
    }
}
