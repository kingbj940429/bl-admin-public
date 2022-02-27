package blaunch.bladmin.service;

import blaunch.bladmin.dto.VerifyBusinessDto;
import blaunch.bladmin.entity.business.Business;
import blaunch.bladmin.entity.business.VerifyBusiness;
import blaunch.bladmin.entity.business.status.VerifyYn;
import blaunch.bladmin.entity.status.SalesStatus;
import blaunch.bladmin.exception.ErrorCode;
import blaunch.bladmin.exception.custom.BusinessNotFoundException;
import blaunch.bladmin.repository.business.BusinessRepository;
import blaunch.bladmin.repository.business.VerifyBusinessRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
@Transactional(readOnly = true)
class BusinessServiceTest {
    @Autowired VerifyBusinessRepository verifyBusinessRepository;
    @Autowired BusinessRepository businessRepository;
    @Autowired EntityManager em;

    @Test
    @Transactional
    @DisplayName("검수 설정")
    void create(){
        //given
        Business business = businessRepository.findFirstByOrderByCreatedDateDesc().orElseThrow(() -> new BusinessNotFoundException(ErrorCode.BUSINESS_NOT_FOUND));
        List<VerifyBusiness> findVerifyBusinesses = verifyBusinessRepository.findByBusinessId(business.getId());

        //when
        verifyBusinessRepository.findByBusinessId(business.getId())
                .forEach(verifyBusiness -> verifyBusinessRepository.delete(verifyBusiness));

        em.flush();

        VerifyBusinessDto.insVerifyBusinessRes build = VerifyBusinessDto.insVerifyBusinessRes.builder()
                .business(business)
                .verifyYn(VerifyYn.Y)
                .verifyType("0001")
                .build();
        VerifyBusiness savedVerifyBusiness = verifyBusinessRepository.save(build.toEntity());

        //then
        assertThat(savedVerifyBusiness.getVerifyYn()).isEqualTo(VerifyYn.Y);
    }

    @Test
    @Transactional
    @DisplayName("판매 상태 변경")
    void updSalesStatus(){
        //given
        Business findBusiness = businessRepository.findFirstByOrderByCreatedDateDesc().orElseThrow(() -> new BusinessNotFoundException(ErrorCode.BUSINESS_NOT_FOUND));

        //when
        findBusiness.updSalesStatus(SalesStatus.Q);
        em.flush();
        em.clear();
        Business updatedBusiness = businessRepository.findFirstByOrderByCreatedDateDesc().orElseThrow(() -> new BusinessNotFoundException(ErrorCode.BUSINESS_NOT_FOUND));

        //then
        assertThat(updatedBusiness.getSalesStatus()).isEqualTo(SalesStatus.Q);
    }

    @Test
    @Transactional
    @DisplayName("비즈니스 게시글 삭제")
    void delete(){
        //given
        Business findBusiness = businessRepository.findFirstByOrderByCreatedDateDesc().orElseThrow(() -> new BusinessNotFoundException(ErrorCode.BUSINESS_NOT_FOUND));

        //when
        businessRepository.delete(findBusiness);
        em.flush();
        em.clear();
        Business findBusiness2 = businessRepository.findFirstByOrderByCreatedDateDesc().orElseThrow(() -> new BusinessNotFoundException(ErrorCode.BUSINESS_NOT_FOUND));

        //then
        assertThat(findBusiness).isNotSameAs(findBusiness2);
    }
}