package blaunch.bladmin.repository;

import blaunch.bladmin.entity.guide.Guide;
import blaunch.bladmin.repository.custom.GuideRepositoryCustom;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface GuideRepository extends JpaRepository<Guide, String>, GuideRepositoryCustom {

    //테스트용
    Optional<Guide> findFirstByOrderByCreatedDateDesc();
}
