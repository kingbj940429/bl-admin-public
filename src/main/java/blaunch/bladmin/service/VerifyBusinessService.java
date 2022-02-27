package blaunch.bladmin.service;

import blaunch.bladmin.dto.VerifyBusinessDto;
import blaunch.bladmin.entity.business.Business;
import blaunch.bladmin.entity.business.VerifyBusiness;
import blaunch.bladmin.entity.business.status.VerifyYn;
import blaunch.bladmin.repository.business.BusinessRepository;
import blaunch.bladmin.repository.business.VerifyBusinessRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class VerifyBusinessService {
    private final VerifyBusinessRepository verifyBusinessRepository;
    private final BusinessRepository businessRepository;
    private final EntityManager em;

    @Transactional
    public List<VerifyBusinessDto.insVerifyBusinessRes> create(List<VerifyBusinessDto.insVerifyBusinessRes> dto, String businessId){
        verifyBusinessRepository.findByBusinessId(businessId)
                .forEach(verifyBusiness -> verifyBusinessRepository.delete(verifyBusiness));
        em.flush();
        Business findBusiness = businessRepository.getById(businessId);

        for (VerifyBusinessDto.insVerifyBusinessRes verifyBusinessRes : dto) {
            VerifyBusinessDto.insVerifyBusinessRes build = VerifyBusinessDto.insVerifyBusinessRes.builder()
                    .business(findBusiness)
                    .verifyYn(verifyBusinessRes.getVerifyYn())
                    .verifyType(verifyBusinessRes.getVerifyType())
                    .build();
            verifyBusinessRepository.save(build.toEntity());
        }

        return dto;
    }
}
