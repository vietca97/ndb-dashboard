package com.neo.dashboard.models;

public class Data {

    private String phone;
    private String smsPackageCode;
    private String smsSyntax;

    public Data(String phone, String smsPackageCode, String smsSyntax) {
        this.phone = phone;
        this.smsPackageCode = smsPackageCode;
        this.smsSyntax = smsSyntax;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getSmsPackageCode() {
        return smsPackageCode;
    }

    public void setSmsPackageCode(String smsPackageCode) {
        this.smsPackageCode = smsPackageCode;
    }

    public String getSmsSyntax() {
        return smsSyntax;
    }

    public void setSmsSyntax(String smsSyntax) {
        this.smsSyntax = smsSyntax;
    }
}
