package blaunch.bladmin.entity.Community;

import blaunch.bladmin.entity.base.BaseTimeEntity;

import javax.persistence.*;

@Entity
@Table(name = "bl_board_click")
public class ViewsCommunity extends BaseTimeEntity {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "click_id")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "board_id")
    private Community community;
}
