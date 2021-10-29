package com.neo.dashboard.models;

public class ResultMessage {

    private Data data;
    private String message;
    private String code;

    public ResultMessage(Data data, String message, String code) {
        this.data = data;
        this.message = message;
        this.code = code;
    }

    public Data getData() {
        return data;
    }

    public void setData(Data data) {
        this.data = data;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }
}
