package blaunch.bladmin.entity.business;

import blaunch.bladmin.entity.base.BaseTimeEntity;
import blaunch.bladmin.entity.business.Business;
import lombok.Getter;

import javax.persistence.*;

@Getter
@Entity
@Table(name = "bl_business_click")
public class ViewsBusiness extends BaseTimeEntity {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "click_id")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "business_id")
    private Business business;
}
