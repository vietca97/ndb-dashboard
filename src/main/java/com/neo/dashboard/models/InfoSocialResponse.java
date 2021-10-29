package com.neo.dashboard.models;

public class InfoSocialResponse {

    private ResponseDTO data;
    private String message;
    private Integer code;

    public InfoSocialResponse(ResponseDTO data, String message, Integer code) {
        this.data = data;
        this.message = message;
        this.code = code;
    }

    @Override
    public String toString() {
        return "InfoSocialResponse{" +
                "data='" + data + '\'' +
                ", message='" + message + '\'' +
                ", code=" + code +
                '}';
    }

    public ResponseDTO getData() {
        return data;
    }

    public void setData(ResponseDTO data) {
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
