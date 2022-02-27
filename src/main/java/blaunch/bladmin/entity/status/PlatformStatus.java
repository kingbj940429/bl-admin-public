package blaunch.bladmin.entity.status;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum PlatformStatus {
    G("Google", "구글 사용자"),
    N("Naver", "네이버 사용자"),
    K("Kakao", "카카오 사용자"),
    X("Blaunch Admin", "비런치 관리자"),
    B("Blaunch User", "비런치 사용자");

    private String code;
    private String name;
}
