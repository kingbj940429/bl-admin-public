package blaunch.bladmin.service;

import blaunch.bladmin.dto.BusinessDto;
import blaunch.bladmin.entity.business.Business;
import blaunch.bladmin.exception.ErrorCode;
import blaunch.bladmin.exception.custom.BusinessNotFoundException;
import blaunch.bladmin.repository.business.BusinessRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class BusinessService {
    private final BusinessRepository businessRepository;

    @Transactional
    public BusinessDto.updSalesStatusRes updSalesStatus(String businessId, BusinessDto.updSalesStatusRes dto){
        Business findBusiness = businessRepository.findById(businessId).orElseThrow(() -> new BusinessNotFoundException(ErrorCode.BUSINESS_NOT_FOUND));
        findBusiness.updSalesStatus(dto.getSalesStatus());

        return dto;
    }

    @Transactional
    public BusinessDto.deleteRes delete(String businessId){
        Business findBusiness = businessRepository.findById(businessId).orElseThrow(() -> new BusinessNotFoundException(ErrorCode.BUSINESS_NOT_FOUND));
        businessRepository.delete(findBusiness);

        return BusinessDto.deleteRes.builder().business(findBusiness).build();
    }
}
