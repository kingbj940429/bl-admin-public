package blaunch.bladmin.repository.business;

import blaunch.bladmin.entity.business.Business;
import blaunch.bladmin.repository.custom.BusinessRepositoryCustom;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface BusinessRepository extends JpaRepository<Business, String>, BusinessRepositoryCustom {


    //테스트용
    Optional<Business> findFirstByOrderByCreatedDateDesc();
}
