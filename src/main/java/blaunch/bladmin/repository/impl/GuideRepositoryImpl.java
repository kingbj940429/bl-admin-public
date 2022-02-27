package blaunch.bladmin.repository.impl;

import blaunch.bladmin.dto.GuideDto;
import blaunch.bladmin.dto.QGuideDto_FindGuideByIdRes;
import blaunch.bladmin.dto.QGuideDto_FindGuidesRes;
import blaunch.bladmin.dto.condition.GuideCondition;
import blaunch.bladmin.entity.guide.QViewsGuide;
import blaunch.bladmin.repository.condition.GuideRepositoryCondition;
import blaunch.bladmin.repository.custom.GuideRepositoryCustom;
import com.querydsl.jpa.JPAExpressions;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Optional;

import static blaunch.bladmin.entity.QAccount.account;
import static blaunch.bladmin.entity.guide.QGuide.guide;
import static blaunch.bladmin.entity.guide.QViewsGuide.*;
import static com.querydsl.jpa.JPAExpressions.*;

@RequiredArgsConstructor
public class GuideRepositoryImpl implements GuideRepositoryCustom {
    private final JPAQueryFactory queryFactory;

    @Override
    public Page<GuideDto.FindGuidesRes> findGuides(GuideCondition condition, Pageable pageable) {
        List<GuideDto.FindGuidesRes> findGuidesRes = queryFactory
                .select(
                        new QGuideDto_FindGuidesRes(
                                guide
                        )
                )
                .from(guide)
                .leftJoin(guide.account, account).fetchJoin()
                .offset(pageable.getOffset())
                .limit(pageable.getPageSize())
                .fetch();

        Long count = queryFactory
                .select(guide.count())
                .from(guide)
                .fetchOne();

        return new PageImpl(findGuidesRes, pageable, count);
    }

    @Override
    public Optional<GuideDto.FindGuideByIdRes> findGuideById(GuideCondition condition) {
        GuideDto.FindGuideByIdRes findGuideByIdRes = queryFactory
                .select(
                        new QGuideDto_FindGuideByIdRes(
                                guide,
                                select(viewsGuide.id.count())
                                        .from(viewsGuide)
                                        .where(
                                                GuideRepositoryCondition.idEq(condition.getId())
                                        )
                        )
                )
                .from(guide)
                .where(
                        GuideRepositoryCondition.idEq(condition.getId())
                )
                .fetchOne();

        return Optional.ofNullable(findGuideByIdRes);
    }

}
