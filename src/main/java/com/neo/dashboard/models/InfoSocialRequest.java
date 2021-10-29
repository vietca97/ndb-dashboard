package com.neo.dashboard.models;

public class InfoSocialRequest {

    private String phone;
    private String pakageCode;
    private int status;

    public InfoSocialRequest(String phone, String pakageCode, int status) {
        this.phone = phone;
        this.pakageCode = pakageCode;
        this.status =  status;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getPakageCode() {
        return pakageCode;
    }

    public void setPakageCode(String pakageCode) {
        this.pakageCode = pakageCode;
    }
}
