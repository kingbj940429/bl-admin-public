package blaunch.bladmin.dto.condition;

import blaunch.bladmin.entity.status.FavoriteYn;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class FavoriteBusinessCondition {
    private Long id;
    private FavoriteYn favoriteYn;
}
