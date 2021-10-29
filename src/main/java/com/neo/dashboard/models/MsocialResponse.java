package com.neo.dashboard.models;

public class MsocialResponse {

    private MsocialRequest data;
    private String message;
    private Integer code;

    public MsocialResponse(MsocialRequest data, String message, Integer code) {
        this.data = data;
        this.message = message;
        this.code = code;
    }

    public MsocialRequest getData() {
        return data;
    }

    public void setData(MsocialRequest data) {
        this.data = data;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }
}
