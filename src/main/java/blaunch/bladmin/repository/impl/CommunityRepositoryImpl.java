package blaunch.bladmin.repository.impl;

import blaunch.bladmin.dto.CommunityDto;
import blaunch.bladmin.dto.QCommunityDto_FindCommunityRes;
import blaunch.bladmin.dto.condition.CommunityCondition;
import blaunch.bladmin.entity.Community.Community;
import blaunch.bladmin.entity.status.FavoriteYn;
import blaunch.bladmin.repository.condition.CommentRepositoryCondition;
import blaunch.bladmin.repository.condition.CommunityRepositoryCondition;
import blaunch.bladmin.repository.condition.FavoriteCommunityRepositoryCondition;
import blaunch.bladmin.repository.condition.ViewsCommunityRepositoryCondition;
import blaunch.bladmin.repository.custom.CommunityRepositoryCustom;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

import static blaunch.bladmin.entity.Community.QCommunity.community;
import static blaunch.bladmin.entity.Community.QFavoriteCommunity.favoriteCommunity;
import static blaunch.bladmin.entity.Community.QViewsCommunity.viewsCommunity;
import static blaunch.bladmin.entity.QAccount.account;
import static blaunch.bladmin.entity.QComment.comment;
import static com.querydsl.jpa.JPAExpressions.select;

@Component
@RequiredArgsConstructor
public class CommunityRepositoryImpl implements CommunityRepositoryCustom {
    private final JPAQueryFactory queryFactory;

    @Override
    public Page<Community> findCommunities(CommunityCondition condition, Pageable pageable) {
        List<Community> communities = queryFactory
                .selectFrom(community)
                .where()
                .innerJoin(community.account, account).fetchJoin()
                .offset(pageable.getOffset())
                .limit(pageable.getPageSize())
                .orderBy(community.createdDate.desc())
                .fetch();

        Long count = queryFactory
                .select(community.count())
                .from(community)
                .fetchOne();

        return new PageImpl(communities, pageable, count);
    }

    @Override
    public Optional<CommunityDto.FindCommunityRes> findCommunity(CommunityCondition condition) {

        CommunityDto.FindCommunityRes findCommunityRes = queryFactory
                .select(
                        new QCommunityDto_FindCommunityRes(
                                community,
                                select(viewsCommunity.id.count())
                                        .from(viewsCommunity)
                                        .where(
                                                ViewsCommunityRepositoryCondition.communityIdEq(condition.getId())
                                        ),
                                select(favoriteCommunity.id.count())
                                        .from(favoriteCommunity)
                                        .where(
                                                FavoriteCommunityRepositoryCondition.communityIdEq(condition.getId()),
                                                FavoriteCommunityRepositoryCondition.favoriteYnEq(FavoriteYn.Y)
                                        ),
                                select(comment.id.count())
                                        .from(comment)
                                        .where(
                                                CommentRepositoryCondition.typeIdEq(condition.getId())
                                        )
                        )
                )
                .from(community)
                .where(
                        CommunityRepositoryCondition.idEq(condition.getId())
                )
                .innerJoin(community.account, account).fetchJoin()
                .fetchOne();


        return Optional.ofNullable(findCommunityRes);
    }


}
