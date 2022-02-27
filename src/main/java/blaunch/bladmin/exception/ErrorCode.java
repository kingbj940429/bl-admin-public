package blaunch.bladmin.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum ErrorCode {

    ROLE_NOT_FOUND("AU_001", "해당 권한을 찾을 수 없습니다.", 404),

    ACCOUNT_NOT_FOUND("AC_001", "해당 회원을 찾을 수 없습니다.", 404),
    ACCOUNT_NOT_VALID("AC_002", "회원 정보가 일치하지 않습니다.", 404),
    EMAIL_DUPLICATION("AC_003", "이메일이 중복되었습니다.", 400),
    USERID_DUPLICATION("AC_004", "계정이 중복되었습니다.", 400),

    BUSINESS_NOT_FOUND("BU_001", "해당 비즈니스를 찾을 수 없습니다.", 404),

    COMMUNITY_NOT_FOUND("CO_001", "해당 커뮤니티 게시글을 찾을 수 없습니다.", 404),

    GUIDE_NOT_FOUND("GU_001", "해당 가이드를 찾을 수 없습니다.", 404),

    CS_NOT_FOUND("CS_001", "해당 CS를 찾을 수 없습니다.", 404),

    INPUT_VALUE_INVALID("IN_001", "입력값이 올바르지 않습니다.", 400);

    private final String code;
    private final String message;
    private final int status;
}
