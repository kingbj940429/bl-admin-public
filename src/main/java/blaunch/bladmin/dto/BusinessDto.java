package blaunch.bladmin.dto;

import blaunch.bladmin.entity.business.Business;
import blaunch.bladmin.entity.business.SalesBusiness;
import blaunch.bladmin.entity.business.VerifyBusiness;
import blaunch.bladmin.entity.status.InspectionStatus;
import blaunch.bladmin.entity.status.SalesStatus;
import com.querydsl.core.annotations.QueryProjection;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import static java.util.stream.Collectors.toList;

public class BusinessDto {

    @Getter
    public static class FindBusinessesRes {
        private String id;
        private String nickname;
        private String businessType;
        private String title;
        private LocalDateTime createdDate;
        private SalesStatus salesStatus;
        private InspectionStatus inspectionStatus;

        public FindBusinessesRes(Business business) {
            this.nickname = business.getAccount().getNickname();
            this.id = business.getId();
            this.businessType = business.getBusinessType();
            this.title = business.getTitle();
            this.createdDate = business.getCreatedDate();
            this.salesStatus = business.getSalesStatus();
            this.inspectionStatus = business.getInspectionStatus();
        }
    }

    @Getter
    public static class FindBusinessRes {
        //분류, 활성화 제외
        private LocalDateTime createdDate;
        private String id;
        private InspectionStatus inspectionStatus;
        private String businessType;
        private String nickname;
        private Long viewsCnt;
        private String userName;
        private Long favoriteCnt;
        private String userId;
        private SalesStatus salesStatus;
        private String userPhone;

        private String title;
        private String address;
        private String startDay;
        private String category;
        private String platform;
        private String introduce;
        private double businessPrice;
        private List<SalesBusinessDto.FindSalesBusinessRes> salesBusinesses = new ArrayList<>();
        private double wholeMaxPrice;
        private int favoriteZZimCnt;
        private int monAvgVisitorCnt;
        private int followerCnt;
        private int downloadCnt;
        private String exposeSelect;
        private String extraSales;
        private List<VerifyBusinessDto.FindVerifyBusinessesRes> verifyBusinesses = new ArrayList<>();

        private List<VerifyBusinessDto.FindVerifyBusinessesRes> initFindVerifyBusinessesRes(List<VerifyBusiness> verifyBusinesses){
            List<VerifyBusinessDto.FindVerifyBusinessesRes> findVerifyBusinessesRes = verifyBusinesses.stream()
                    .map(verifyBusiness -> new VerifyBusinessDto.FindVerifyBusinessesRes(verifyBusiness))
                    .collect(toList());

            return (findVerifyBusinessesRes == null) ? new ArrayList<>() : findVerifyBusinessesRes;
        }

        private List<SalesBusinessDto.FindSalesBusinessRes> initFindSalesBusinessRes(List<SalesBusiness> salesBusinesses){
            List<SalesBusinessDto.FindSalesBusinessRes> findSalesBusinessRes = salesBusinesses.stream()
                    .map(salesBusiness -> new SalesBusinessDto.FindSalesBusinessRes(salesBusiness))
                    .collect(toList());

            return (findSalesBusinessRes == null) ? new ArrayList<>() : findSalesBusinessRes;
        }

        @QueryProjection
        public FindBusinessRes(Business business, Long favoriteCnt, Long viewsCnt) {
            business.inspectionStatus(business.getVerifyBusinesses());

            this.createdDate = business.getCreatedDate();
            this.id = business.getId();
            this.inspectionStatus = business.getInspectionStatus();
            this.businessType = business.getBusinessType();
            this.nickname = business.getAccount().getNickname();
            this.viewsCnt = viewsCnt;
            this.userName = business.getAccount().getUserName();
            this.favoriteCnt = favoriteCnt;
            this.userId = business.getAccount().getUserId();
            this.salesStatus = business.getSalesStatus();
            this.userPhone = business.getAccount().getUserPhone();

            this.title = business.getTitle();
            this.address = business.getAddress();
            this.startDay = business.getStartDay();
            this.category = business.getCategory();
            this.platform = business.getPlatform();
            this.introduce = business.getIntroduce();
            this.businessPrice = business.getBusinessPrice();
            this.salesBusinesses = initFindSalesBusinessRes(business.getSalesBusinesses());
            this.wholeMaxPrice = business.getWholeMaxPrice();
            this.favoriteZZimCnt = business.getFavoriteCnt();
            this.monAvgVisitorCnt = business.getMonAvgVisitorCnt();
            this.followerCnt = business.getFollowerCnt();
            this.downloadCnt = business.getDownloadCnt();
            this.exposeSelect = business.getExposeSelect();
            this.extraSales = business.getExtraSales();
            this.verifyBusinesses = initFindVerifyBusinessesRes(business.getVerifyBusinesses());
        }
    }

    @Getter
    @NoArgsConstructor(access = AccessLevel.PROTECTED)
    public static class updSalesStatusRes {
        @NotNull
        private SalesStatus salesStatus;

        public updSalesStatusRes(Business business) {
            this.salesStatus = business.getSalesStatus();
        }
    }

    @Getter
    public static class deleteRes {
        private String id;

        @Builder
        public deleteRes(Business business) {
            this.id = business.getId();
        }
    }
}
