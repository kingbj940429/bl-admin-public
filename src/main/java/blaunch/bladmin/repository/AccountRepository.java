package blaunch.bladmin.repository;

import blaunch.bladmin.entity.Account;
import blaunch.bladmin.entity.status.PlatformStatus;
import blaunch.bladmin.repository.custom.AccountRepositoryCustom;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface AccountRepository extends JpaRepository<Account, String>, AccountRepositoryCustom {
    Optional<Account> findById(String id);
    Optional<Account> findOneByUserId(String userId);
    Optional<Account> findOneByUserIdAndPlatformStatus(String userId, PlatformStatus platformStatus);

    //테스트용
    Optional<Account> findFirstByOrderByCreatedDateDesc();
}
