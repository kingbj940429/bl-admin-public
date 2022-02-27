package blaunch.bladmin.repository;

import blaunch.bladmin.entity.Community.Community;
import blaunch.bladmin.entity.business.Business;
import blaunch.bladmin.repository.custom.CommunityRepositoryCustom;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface CommunityRepository extends JpaRepository<Community, String>, CommunityRepositoryCustom {
    Optional<Community> findByTitle(String title);

    //테스트용
    Optional<Community> findFirstByOrderByCreatedDateDesc();
    Community findByContentsAndTitle(String contents, String title);
}
