package blaunch.bladmin.repository.condition;

import com.querydsl.core.types.dsl.BooleanExpression;
import org.springframework.util.StringUtils;

import static blaunch.bladmin.entity.business.QViewsBusiness.viewsBusiness;


public class ViewsBusinessRepositoryCondition {

    public static BooleanExpression businessIdEq(String businessId){
        return StringUtils.hasText(businessId) ? viewsBusiness.business.id.eq(businessId) : null;
    }
}
