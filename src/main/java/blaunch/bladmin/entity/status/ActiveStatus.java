package blaunch.bladmin.entity.status;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum ActiveStatus {
    ENABLE("ENABLE", "활성화"),
    DISENABLE("DISENABLE", "비활성화");

    private String code;
    private String name;
}
