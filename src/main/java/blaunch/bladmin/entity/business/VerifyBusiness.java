package blaunch.bladmin.entity.business;

import blaunch.bladmin.entity.base.BaseTimeEntity;
import blaunch.bladmin.entity.business.Business;
import blaunch.bladmin.entity.business.status.VerifyYn;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

import javax.persistence.*;

@Getter
@Builder
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@Entity
@Table(name = "bl_verifi_business")
public class VerifyBusiness extends BaseTimeEntity {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "verifi_id")
    private Long Id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "business_id")
    private Business business;

    @Column(name = "veri_type", unique = true)
    private String verifyType;//0001:평균매출 0002:최고매출 0003 : 시작일

    @Enumerated(EnumType.STRING)
    @Column(name = "veri_yn")
    private VerifyYn verifyYn;
}
