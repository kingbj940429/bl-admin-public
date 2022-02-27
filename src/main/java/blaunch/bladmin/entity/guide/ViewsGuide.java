package blaunch.bladmin.entity.guide;

import blaunch.bladmin.entity.base.BaseTimeEntity;

import javax.persistence.*;

@Entity
@Table(name = "bl_guide_click")
public class ViewsGuide extends BaseTimeEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "click_id")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "guide_id")
    private Guide guide;
}
