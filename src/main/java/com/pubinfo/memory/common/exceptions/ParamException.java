package com.pubinfo.memory.common.exceptions;


public class ParamException extends RuntimeException {

    private static final long serialVersionUID = 1;

    private final String paramInfo;

    public ParamException(String paramInfo) {
        this.paramInfo = paramInfo;
    }

    public String getErrorMessage( ) {
        return paramInfo;
    }

}
