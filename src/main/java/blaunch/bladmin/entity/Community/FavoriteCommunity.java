package blaunch.bladmin.entity.Community;

import blaunch.bladmin.entity.Account;
import blaunch.bladmin.entity.base.BaseTimeEntity;
import blaunch.bladmin.entity.business.Business;
import blaunch.bladmin.entity.status.FavoriteYn;
import lombok.Getter;

import javax.persistence.*;

@Getter
@Entity
@Table(name = "bl_favorite_board")
public class FavoriteCommunity extends BaseTimeEntity {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "favorite_id")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "account_id")
    private Account account;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "board_id")
    private Community community;

    @Enumerated(EnumType.STRING)
    @Column(name = "favorite_yn")
    private FavoriteYn favoriteYn;
}
