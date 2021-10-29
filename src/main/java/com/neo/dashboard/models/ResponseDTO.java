package com.neo.dashboard.models;

public class ResponseDTO {

    private String phone;
    private Integer status;
    private String smsPackageCode;
    private String smsSyntax;

    public String getSmsSyntax() {
        return smsSyntax;
    }

    public void setSmsSyntax(String smsSyntax) {
        this.smsSyntax = smsSyntax;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public String getSmsPackageCode() {
        return smsPackageCode;
    }

    public void setSmsPackageCode(String smsPackageCode) {
        this.smsPackageCode = smsPackageCode;
    }

    public ResponseDTO(String phone, Integer status, String smsPackageCode, String smsSyntax) {
        this.phone = phone;
        this.status = status;
        this.smsPackageCode = smsPackageCode;
        this.smsSyntax = smsSyntax;
    }
}
