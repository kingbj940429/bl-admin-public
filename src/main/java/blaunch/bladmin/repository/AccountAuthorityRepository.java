package blaunch.bladmin.repository;

import blaunch.bladmin.entity.AccountAuthority;
import blaunch.bladmin.repository.custom.AccountAuthorityRepositoryCustom;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface AccountAuthorityRepository extends JpaRepository<AccountAuthority, Long>, AccountAuthorityRepositoryCustom {
    Optional<AccountAuthority> findFirstByOrderByAccountAsc();
}
