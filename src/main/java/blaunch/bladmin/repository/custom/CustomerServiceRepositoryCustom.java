package blaunch.bladmin.repository.custom;

import blaunch.bladmin.dto.CustomerServiceDto;
import blaunch.bladmin.dto.condition.CustomerServiceCondition;
import blaunch.bladmin.entity.CsAnswer;
import blaunch.bladmin.entity.CustomerService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

public interface CustomerServiceRepositoryCustom {
    Page<CustomerService> findCss(CustomerServiceCondition condition, Pageable pageable);
}
