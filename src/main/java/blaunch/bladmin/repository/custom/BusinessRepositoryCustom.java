package blaunch.bladmin.repository.custom;

import blaunch.bladmin.dto.BusinessDto;
import blaunch.bladmin.dto.condition.BusinessCondition;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

public interface BusinessRepositoryCustom {
    Page<BusinessDto.FindBusinessesRes> findBusinesses(BusinessCondition condition, Pageable pageable);
    Optional<BusinessDto.FindBusinessRes> findBusiness(BusinessCondition condition);
}
