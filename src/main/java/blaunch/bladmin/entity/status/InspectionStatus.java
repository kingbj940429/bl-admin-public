package blaunch.bladmin.entity.status;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum InspectionStatus {
    ALL_COM("A", "검수 완료"),
    PART_COM("P", "일부 검수"),
    DENY("D", "보류"),
    WAIT("W", "대기중");

    private String code;
    private String name;
}
