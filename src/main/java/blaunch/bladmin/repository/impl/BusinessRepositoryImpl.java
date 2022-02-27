package blaunch.bladmin.repository.impl;

import blaunch.bladmin.dto.BusinessDto;
import blaunch.bladmin.dto.QBusinessDto_FindBusinessRes;
import blaunch.bladmin.dto.condition.BusinessCondition;
import blaunch.bladmin.entity.business.Business;
import blaunch.bladmin.entity.status.FavoriteYn;
import blaunch.bladmin.repository.condition.BusinessRepositoryCondition;
import blaunch.bladmin.repository.condition.FavoriteBusinessRepositoryCondition;
import blaunch.bladmin.repository.condition.ViewsBusinessRepositoryCondition;
import blaunch.bladmin.repository.custom.BusinessRepositoryCustom;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

import static blaunch.bladmin.entity.QAccount.account;
import static blaunch.bladmin.entity.business.QBusiness.business;
import static blaunch.bladmin.entity.business.QFavoriteBusiness.favoriteBusiness;
import static blaunch.bladmin.entity.business.QViewsBusiness.viewsBusiness;
import static blaunch.bladmin.repository.condition.BusinessRepositoryCondition.salesStatusEq;
import static com.querydsl.jpa.JPAExpressions.select;
import static java.util.stream.Collectors.toList;

@Component
@RequiredArgsConstructor
public class BusinessRepositoryImpl implements BusinessRepositoryCustom {
    private final JPAQueryFactory queryFactory;

    @Override
    public Page<BusinessDto.FindBusinessesRes> findBusinesses(BusinessCondition condition, Pageable pageable) {
        List<Business> businesses = queryFactory
                .selectFrom(business)
                .where(
                        salesStatusEq(condition.getSalesStatus())
                )
                .innerJoin(business.account, account).fetchJoin()
                .offset(pageable.getOffset())
                .limit(pageable.getPageSize())
                .orderBy(business.createdDate.desc())
                .fetch();

        businesses.forEach(business -> business.inspectionStatus(business.getVerifyBusinesses()));

        List<BusinessDto.FindBusinessesRes> findBusinessesRes = businesses.stream()
                .filter((business1) -> {
                    if(condition.getInspectionStatus() != null) return business1.getInspectionStatus().equals(condition.getInspectionStatus());
                    return true;
                })
                .map(business1 -> new BusinessDto.FindBusinessesRes(business1)).collect(toList());

        Long count = queryFactory.select(business.count())
                .from(business)
                .fetchOne();

        return new PageImpl(findBusinessesRes, pageable, count);
    }

    @Override
    public Optional<BusinessDto.FindBusinessRes> findBusiness(BusinessCondition condition) {

        BusinessDto.FindBusinessRes findBusinessRes = queryFactory
                .select(
                        new QBusinessDto_FindBusinessRes(
                                business,
                                select(favoriteBusiness.id.count())
                                        .from(favoriteBusiness)
                                        .where(
                                                FavoriteBusinessRepositoryCondition.businessIdEq(condition.getId()),
                                                FavoriteBusinessRepositoryCondition.favoriteYnEq(FavoriteYn.Y)
                                        ),
                                select(viewsBusiness.id.count())
                                        .from(viewsBusiness)
                                        .where(
                                                ViewsBusinessRepositoryCondition.businessIdEq(condition.getId())
                                        )
                        )
                )
                .from(business)
                .where(
                        BusinessRepositoryCondition.idEq(condition.getId())
                )
                .fetchOne();

        return Optional.ofNullable(findBusinessRes);
    }

}
