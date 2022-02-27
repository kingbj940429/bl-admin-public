package blaunch.bladmin.repository.condition;

import blaunch.bladmin.entity.Community.QFavoriteCommunity;
import blaunch.bladmin.entity.status.FavoriteYn;
import com.querydsl.core.types.dsl.BooleanExpression;
import org.springframework.util.StringUtils;

import static blaunch.bladmin.entity.Community.QFavoriteCommunity.favoriteCommunity;


public class FavoriteCommunityRepositoryCondition {
    public static BooleanExpression communityIdEq(String communityId){
        return StringUtils.hasText(communityId) ? favoriteCommunity.community.id.eq(communityId) : null;
    }

    public static BooleanExpression favoriteYnEq(FavoriteYn favoriteYn){
        return StringUtils.hasText(String.valueOf(favoriteYn)) ? favoriteCommunity.favoriteYn.eq(favoriteYn) : null;
    }
}
