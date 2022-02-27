package blaunch.bladmin.repository;

import blaunch.bladmin.entity.Token;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface TokenRepository extends JpaRepository<Token, Long> {
    Token findByAccount_Id(String accountId);
}
