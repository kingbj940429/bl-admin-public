package blaunch.bladmin.repository.business;

import blaunch.bladmin.entity.business.FavoriteBusiness;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface FavoriteBusinessRepository extends JpaRepository<FavoriteBusiness, Long> {

    @Query("select count(b) from FavoriteBusiness b where b.business.id = :businessId and b.favoriteYn = 'Y'")
    int countFavoriteBusinessesByBusiness_Id(@Param("businessId") String businessId);
}
