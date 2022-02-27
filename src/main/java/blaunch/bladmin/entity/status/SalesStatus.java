package blaunch.bladmin.entity.status;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum SalesStatus {
    S("SALE", "판매중"),
    C("SOLD_OUT", "판매완료"),
    Q("SALE_REQ", "판매요청"),
    R("RESERVED", "예약됨"),
    T("TEMP", "임시 저장 비즈니스"),
    F("INSPECTION_BEFORE", "검수 전"),
    U("Disabled", "비활성화");

    private String code;
    private String name;
}
