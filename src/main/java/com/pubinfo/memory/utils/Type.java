package com.pubinfo.memory.utils;

public enum Type {
    B(1,"B"), KB(1024,"KB"), M(1024 * 1024,"M");

    private Integer code;
    private String type;


    Type(Integer code, String type) {
        this.code = code;
        this.type = type;
    }

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }


    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
}