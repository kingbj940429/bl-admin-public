package blaunch.bladmin.dto;

import blaunch.bladmin.entity.business.Business;
import blaunch.bladmin.entity.business.VerifyBusiness;
import blaunch.bladmin.entity.business.status.VerifyYn;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;

public class VerifyBusinessDto {

    @Getter
    public static class FindVerifyBusinessesRes {
        private String verifyType;
        private VerifyYn verifyYn;

        public FindVerifyBusinessesRes(VerifyBusiness verifyBusiness) {
            this.verifyType = verifyBusiness.getVerifyType();
            this.verifyYn = verifyBusiness.getVerifyYn();
        }
    }

    @Getter
    @NoArgsConstructor(access = AccessLevel.PROTECTED)
    public static class insVerifyBusinessRes {
        @NotNull
        private String verifyType;
        @NotNull
        private VerifyYn verifyYn;
        @JsonIgnore
        private Business business;

        @Builder
        public insVerifyBusinessRes(String verifyType, VerifyYn verifyYn, Business business) {
            this.verifyType = verifyType;
            this.verifyYn = verifyYn;
            this.business = business;
        }

        public VerifyBusiness toEntity(){
            return VerifyBusiness.builder()
                    .business(this.business)
                    .verifyType(this.verifyType)
                    .verifyYn(this.verifyYn)
                    .build();
        }
    }
}
