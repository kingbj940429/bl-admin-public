package blaunch.bladmin.exception.custom;

import blaunch.bladmin.exception.ErrorCode;
import lombok.Getter;

@Getter
public class UserIdDuplicationException extends RuntimeException{
    private String code;
    private String message;
    private int status;

    public UserIdDuplicationException(ErrorCode errorCode) {
        this.code = errorCode.getCode();
        this.message = errorCode.getMessage();
        this.status = errorCode.getStatus();
    }
}
