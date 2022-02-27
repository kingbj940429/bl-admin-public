package blaunch.bladmin.repository.business;

import blaunch.bladmin.dto.VerifyBusinessDto;
import blaunch.bladmin.entity.business.VerifyBusiness;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface VerifyBusinessRepository extends JpaRepository<VerifyBusiness, Long> {
    List<VerifyBusiness> findByBusinessId(String businessId);
}
