package blaunch.bladmin.entity.status;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum BusinessType {
    E_COMMERCE("0001", "이커머스"),
    SOCIAL_CONT("0002", "소설 컨텐츠"),
    APP("0003", "어플"),
    SAAS_PRO("0004", "SAAS/프로그램"),
    DOMAIN_SITE("0005", "도메인 사이트"),
    ETC("0006", "기타");


    private String code;
    private String name;
}
