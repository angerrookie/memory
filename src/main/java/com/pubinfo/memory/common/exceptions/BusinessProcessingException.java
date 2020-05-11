package com.pubinfo.memory.common.exceptions;

public class BusinessProcessingException extends RuntimeException{

    private static final long serialVersionUID = 1;

    private final String errorInfo;

    public BusinessProcessingException(String errorInfo) {
        this.errorInfo = errorInfo;
    }

    public String getErrorMessage( ) {
        return errorInfo;
    }

}
