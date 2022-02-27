package blaunch.bladmin.repository;

import blaunch.bladmin.entity.CustomerService;
import blaunch.bladmin.entity.guide.Guide;
import blaunch.bladmin.repository.custom.CustomerServiceRepositoryCustom;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface CustomerServiceRepository extends JpaRepository<CustomerService, String>, CustomerServiceRepositoryCustom {

    //테스트용
    Optional<CustomerService> findFirstByOrderByCreatedDateDesc();
}
