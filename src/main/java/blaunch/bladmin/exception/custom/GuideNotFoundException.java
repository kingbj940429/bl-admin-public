package blaunch.bladmin.exception.custom;

import blaunch.bladmin.exception.ErrorCode;

public class GuideNotFoundException extends RuntimeException{
    private String code;
    private String message;
    private int status;

    public GuideNotFoundException(ErrorCode errorCode) {
        this.code = errorCode.getCode();
        this.message = errorCode.getMessage();
        this.status = errorCode.getStatus();
    }
}
