package com.bupt.disaster_encode.enums;

public enum ExceptionEnum {

    SUCCESS(0, "SUCCESS"),

    ParamInvalid(100001, "Param Invalid"),

    AuthenticationError(200001, "Authentication Error"),

    CheckInconsistentError(300001, "Inconsistent Check Results Exist"),

    SystemError(900001, "System Error");


    private int returnCode;

    private String returnMsg;

    ExceptionEnum(int returnCode, String returnMsg) {
        this.returnCode = returnCode;
        this.returnMsg = returnMsg;
    }

    public int getReturnCode() {
        return returnCode;
    }

    public String getReturnMsg() {
        return returnMsg;
    }
}

