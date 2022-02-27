package blaunch.bladmin.entity.business;

import blaunch.bladmin.entity.Account;
import blaunch.bladmin.entity.base.BaseTimeEntity;
import blaunch.bladmin.entity.status.FavoriteYn;
import lombok.Getter;

import javax.persistence.*;

@Getter
@Entity
@Table(name = "bl_favorite_business")
public class FavoriteBusiness extends BaseTimeEntity {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "favorite_id")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "account_id")
    private Account account;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "business_id")
    private Business business;

    @Enumerated(EnumType.STRING)
    @Column(name = "favorite_yn")
    private FavoriteYn favoriteYn;

    @Transient
    private Long viewsCnt;
}
