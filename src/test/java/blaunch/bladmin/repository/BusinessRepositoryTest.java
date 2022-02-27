package blaunch.bladmin.repository;

import blaunch.bladmin.dto.BusinessDto;
import blaunch.bladmin.dto.condition.BusinessCondition;
import blaunch.bladmin.entity.business.Business;
import blaunch.bladmin.entity.status.InspectionStatus;
import blaunch.bladmin.entity.status.SalesStatus;
import blaunch.bladmin.exception.ErrorCode;
import blaunch.bladmin.exception.custom.BusinessNotFoundException;
import blaunch.bladmin.repository.business.BusinessRepository;
import blaunch.bladmin.repository.business.VerifyBusinessRepository;
import blaunch.bladmin.repository.impl.BusinessRepositoryImpl;
import com.querydsl.jpa.impl.JPAQueryFactory;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import java.util.List;
import java.util.Optional;

@SpringBootTest
@Transactional(readOnly = true)
class BusinessRepositoryTest {
    @Autowired BusinessRepository businessRepository;
    @Autowired BusinessRepositoryImpl businessRepositoryImpl;

    @Autowired VerifyBusinessRepository verifyBusinessRepository;
    @Autowired EntityManager em;
    @Autowired JPAQueryFactory queryFactory;

    @BeforeEach
    void before(){

    }

    @Test
    @DisplayName("비즈니스 조회")
    void findBusinesses(){
        //given
        PageRequest pageRequest = PageRequest.of(0, 5, Sort.Direction.DESC, "regtm");

        //when
        BusinessCondition condition = BusinessCondition.builder().salesStatus(SalesStatus.S).inspectionStatus(InspectionStatus.ALL_COM).build();
        Page<BusinessDto.FindBusinessesRes> businesses = businessRepositoryImpl.findBusinesses(condition, pageRequest);
        List<BusinessDto.FindBusinessesRes> businessesRes = businesses.getContent();

        //then
    }

    @Test
    @DisplayName("단일 비즈니스 조회")
    void findBusiness(){
        //given
        Business business = businessRepository.findFirstByOrderByCreatedDateDesc().orElseThrow(RuntimeException::new);
        BusinessCondition condition = BusinessCondition.builder().id(business.getId()).build();

        //when
        BusinessDto.FindBusinessRes findBusinessRes = businessRepositoryImpl.findBusiness(condition).orElseThrow(() -> new BusinessNotFoundException(ErrorCode.BUSINESS_NOT_FOUND));
        //then

    }
}