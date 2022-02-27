package blaunch.bladmin.entity.status;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum CustomerServiceKind {
    R("Report", "신고하기 및 탈퇴하기"),
    I("Inquiry", "문의하기"),
    A("Notice", "공지사항");

    private String code;
    private String name;
}
