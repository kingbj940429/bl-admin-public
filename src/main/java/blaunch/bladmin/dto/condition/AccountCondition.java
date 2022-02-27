package blaunch.bladmin.dto.condition;

import blaunch.bladmin.entity.status.PlatformStatus;
import blaunch.bladmin.entity.status.UserStatus;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Builder
public class AccountCondition {
    private PlatformStatus platform;
    private String userId;
    private String gender;
    private String job;
    private String birthday;
    private String activeField;
    private UserStatus userStatus;
}
