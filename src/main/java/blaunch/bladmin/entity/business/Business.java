package blaunch.bladmin.entity.business;

import blaunch.bladmin.entity.Account;
import blaunch.bladmin.entity.base.BaseTimeEntity;
import blaunch.bladmin.entity.business.status.VerifyYn;
import blaunch.bladmin.entity.status.*;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Getter
@Entity
@Builder
@Table(name = "bl_business")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
public class Business extends BaseTimeEntity {
    @Id
    @GeneratedValue(generator = "uuid2")
    @GenericGenerator(name = "uuid2", strategy = "uuid2")
    @Column(name = "business_id")
    private String id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "account_id")
    private Account account;

    @Column(name = "business_type")
    private String businessType;

    @Column(name = "img_link")
    private String imgLink;

    private String title;
    private String introduce;

    @Column(name = "start_day")
    private String startDay;//시작일

    private String address;
    private String category;

    @Enumerated(EnumType.STRING)
    @Column(name = "sales_yn")
    private SalesYn salesYn;//매출여부
    private String platform;

    @Column(name = "favorite_cnt")
    private int favoriteCnt;//찜수(int) -- 이커머스만 해당

    @Column(name = "day_avg_visitor_cnt")
    private int dayAvgVisitorCnt;//방문자수(int)  -- 이커머스,어플 제외 하고 나머지 해당

    @Column(name = "mon_avg_visitor_cnt")
    private int monAvgVisitorCnt;//월 평균 방문자수

    @Column(name = "follower_cnt")
    private int followerCnt;//방문자수(int)  -- 이커머스,어플 제외 하고 나머지 해당

    @Column(name = "expose_select")
    private String exposeSelect;//노출내용선택 -- 이커머스 제외하고 모두 해당

    @Column(name = "download_cnt")
    private int downloadCnt;//다운로드수(int) -- 어플만 해당

    @Enumerated(EnumType.STRING)
    @Column(name = "sales_status")
    private SalesStatus salesStatus;//비즈니스 상태

    @Column(name = "b_price")
    private double businessPrice;//판매가격

    @Column(name = "extra_sales")
    private String extraSales;//추가판매목록

    @Enumerated(EnumType.STRING)
    @Column(name = "support_yn")
    private SupportYn supportYn;//사후지원여부

    @Enumerated(EnumType.STRING)
    @Column(name = "veri_req_yn")
    private VeriReqYn veriReqYn;//검수요청여부

    @Column(name = "margin")
    private int marginRate;//마진율

    @Column(name = "whole_max_price")
    private double wholeMaxPrice;//전체기간최고매출

    @Transient
    @Enumerated(EnumType.STRING)
    private InspectionStatus inspectionStatus;//검수여부

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "business")
    private List<VerifyBusiness> verifyBusinesses = new ArrayList<>();

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "business")
    private List<SalesBusiness> salesBusinesses = new ArrayList<>();

    //==연관관계 메서드==//

    //==생성, 수정 메서드==//
    public void updSalesStatus(SalesStatus salesStatus){
        this.salesStatus = salesStatus;
    }

    //==비즈니스 로직==//
    public void inspectionStatus(List<VerifyBusiness> verifyBusinesses) {
        long verifyCnt = verifyBusinesses.stream()
                .filter(verifyBusiness ->  verifyBusiness.getVerifyYn().equals(VerifyYn.Y))
                .count();
        int size = verifyBusinesses.size();

        if(size == 0){
            this.inspectionStatus = InspectionStatus.WAIT;
        } else if(verifyCnt <= 0){
            this.inspectionStatus = InspectionStatus.DENY;
        } else if(verifyCnt > 2){
            this.inspectionStatus = InspectionStatus.ALL_COM;
        } else{
            this.inspectionStatus = InspectionStatus.PART_COM;
        }
    }

    //==조회 로직==//
}
