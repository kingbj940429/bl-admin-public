package blaunch.bladmin.dto.condition;

import blaunch.bladmin.entity.status.InspectionStatus;
import blaunch.bladmin.entity.status.SalesStatus;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class BusinessCondition {
    private String id;
    private SalesStatus salesStatus;
    private InspectionStatus inspectionStatus;
}
