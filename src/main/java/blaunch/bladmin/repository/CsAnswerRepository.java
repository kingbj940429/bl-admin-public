package blaunch.bladmin.repository;

import blaunch.bladmin.entity.CsAnswer;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface CsAnswerRepository extends JpaRepository<CsAnswer, Long> {
    CsAnswer findByCsId(String csId);
}
