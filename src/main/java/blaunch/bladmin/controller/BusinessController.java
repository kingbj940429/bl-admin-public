package blaunch.bladmin.controller;

import blaunch.bladmin.dto.BusinessDto;
import blaunch.bladmin.dto.ResponseDto;
import blaunch.bladmin.dto.condition.BusinessCondition;
import blaunch.bladmin.entity.status.InspectionStatus;
import blaunch.bladmin.entity.status.SalesStatus;
import blaunch.bladmin.exception.ErrorCode;
import blaunch.bladmin.exception.custom.BusinessNotFoundException;
import blaunch.bladmin.repository.business.BusinessRepository;
import blaunch.bladmin.service.BusinessService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1")
public class BusinessController {

    private final BusinessRepository businessRepository;
    private final BusinessService businessService;

    @GetMapping("/businesses")
    public Page<BusinessDto.FindBusinessesRes> findBusinesses(@PageableDefault(page = 0, size = 10) Pageable pageable,
                                         @RequestParam(value = "salesStatus", required = false) SalesStatus salesStatus,
                                         @RequestParam(value = "inspectionStatus", required = false) InspectionStatus inspectionStatus) {
        BusinessCondition condition = BusinessCondition.builder().salesStatus(salesStatus).inspectionStatus(inspectionStatus).build();
        Page<BusinessDto.FindBusinessesRes> businesses = businessRepository.findBusinesses(condition, pageable);

        return businesses;
    }

    @GetMapping("/businesses/{businessId}")
    public ResponseDto.Single<BusinessDto.FindBusinessRes> findBusiness(@PathVariable("businessId")String id){
        BusinessCondition condition = BusinessCondition.builder().id(id).build();
        BusinessDto.FindBusinessRes findBusinessRes = businessRepository.findBusiness(condition).orElseThrow(() -> new BusinessNotFoundException(ErrorCode.BUSINESS_NOT_FOUND));

        return new ResponseDto.Single(findBusinessRes);
    }

    @PutMapping("/businesses/{businessId}/sales-status")
    public ResponseDto.Single<BusinessDto.updSalesStatusRes> updSalesStatus(@PathVariable String businessId,
                                             @RequestBody BusinessDto.updSalesStatusRes updSalesStatusRes){
        return new ResponseDto.Single(businessService.updSalesStatus(businessId, updSalesStatusRes));
    }

    @DeleteMapping("/businesses/{businessId}")
    public ResponseDto.Single<BusinessDto.deleteRes> delete(@PathVariable String businessId){
        return new ResponseDto.Single<>(businessService.delete(businessId));
    }
}
