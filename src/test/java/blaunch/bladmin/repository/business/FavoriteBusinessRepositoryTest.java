package blaunch.bladmin.repository.business;

import blaunch.bladmin.entity.business.Business;
import blaunch.bladmin.entity.business.FavoriteBusiness;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@SpringBootTest
@Transactional(readOnly = true)
class FavoriteBusinessRepositoryTest {
    @Autowired BusinessRepository businessRepository;
    @Autowired FavoriteBusinessRepository favoriteBusinessRepository;

    @Test
    @DisplayName("좋아요 비즈니스 조회")
    void FavoriteCnt(){
        //given

        //when
        List<FavoriteBusiness> favoriteBusinesses = favoriteBusinessRepository.findAll();
        //then

    }

    @Test
    @DisplayName("비즈니스 별 좋아요 갯수")
    void countFavoriteBusinessesByBusiness_Id(){
        //given
        Business findBusiness = businessRepository.findFirstByOrderByCreatedDateDesc().orElseThrow(RuntimeException::new);

        //when
        int count = favoriteBusinessRepository.countFavoriteBusinessesByBusiness_Id(findBusiness.getId());

        //then
        Assertions.assertThat(count).isGreaterThanOrEqualTo(0);
    }
}