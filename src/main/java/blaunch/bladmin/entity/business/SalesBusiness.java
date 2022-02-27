package blaunch.bladmin.entity.business;

import blaunch.bladmin.entity.base.BaseTimeEntity;
import lombok.Getter;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;

@Getter
@Entity
@Table(name = "bl_sales")
public class SalesBusiness extends BaseTimeEntity {

    @GeneratedValue(generator = "uuid2")
    @GenericGenerator(name = "uuid2", strategy = "uuid2")
    @Id @Column(name = "sales_id")
    private String salesId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "business_id")
    private Business business;

    private String period;//기간

    @Column(name = "sales_price")
    private double salesPrice;//매출
    private double income;//순이익
}
