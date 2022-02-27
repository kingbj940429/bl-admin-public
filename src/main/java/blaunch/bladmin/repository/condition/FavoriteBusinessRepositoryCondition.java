package blaunch.bladmin.repository.condition;

import blaunch.bladmin.entity.status.FavoriteYn;
import com.querydsl.core.types.dsl.BooleanExpression;
import org.springframework.util.StringUtils;

import static blaunch.bladmin.entity.business.QFavoriteBusiness.favoriteBusiness;


public class FavoriteBusinessRepositoryCondition {

    public static BooleanExpression businessIdEq(String businessId){
        return StringUtils.hasText(businessId) ? favoriteBusiness.business.id.eq(businessId) : null;
    }

    public static BooleanExpression favoriteYnEq(FavoriteYn favoriteYn){
        return StringUtils.hasText(String.valueOf(favoriteYn)) ? favoriteBusiness.favoriteYn.eq(favoriteYn) : null;
    }
}
