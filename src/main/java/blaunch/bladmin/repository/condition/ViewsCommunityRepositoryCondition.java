package blaunch.bladmin.repository.condition;

import blaunch.bladmin.entity.Community.QViewsCommunity;
import com.querydsl.core.types.dsl.BooleanExpression;
import org.springframework.util.StringUtils;

import static blaunch.bladmin.entity.Community.QViewsCommunity.*;


public class ViewsCommunityRepositoryCondition {
    public static BooleanExpression communityIdEq(String communityId){
        return StringUtils.hasText(communityId) ? viewsCommunity.community.id.eq(communityId) : null;
    }
}
