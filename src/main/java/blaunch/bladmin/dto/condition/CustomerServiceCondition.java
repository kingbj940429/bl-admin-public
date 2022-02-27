package blaunch.bladmin.dto.condition;

import blaunch.bladmin.entity.status.CustomerServiceKind;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class CustomerServiceCondition {
    private String id;
    private CustomerServiceKind csKind;
}
