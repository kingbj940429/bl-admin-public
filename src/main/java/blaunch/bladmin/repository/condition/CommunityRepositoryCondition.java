package blaunch.bladmin.repository.condition;

import blaunch.bladmin.entity.Community.QCommunity;
import com.querydsl.core.types.dsl.BooleanExpression;
import org.springframework.util.StringUtils;

import static blaunch.bladmin.entity.Community.QCommunity.*;


public class CommunityRepositoryCondition {
    public static BooleanExpression idEq(String id){
        return StringUtils.hasText(id) ? community.id.eq(id) : null;
    }
}
