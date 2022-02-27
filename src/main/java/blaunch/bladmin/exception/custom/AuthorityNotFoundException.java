package blaunch.bladmin.exception.custom;

import blaunch.bladmin.exception.ErrorCode;
import lombok.Getter;

@Getter
public class AuthorityNotFoundException extends RuntimeException{
    private String code;
    private String message;
    private int status;

    public AuthorityNotFoundException(ErrorCode errorCode) {
        this.code = errorCode.getCode();
        this.message = errorCode.getMessage();
        this.status = errorCode.getStatus();
    }
}
