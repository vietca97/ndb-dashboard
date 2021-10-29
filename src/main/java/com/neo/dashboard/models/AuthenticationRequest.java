package com.neo.dashboard.models;

public class AuthenticationRequest {

    private String username;
    private String password;
    private String smsSyntax;
    private String smsPackageCode;
    private String smsLocationCode;
    private String smsStore;
    private String phone;
    private Integer status;

    public String getSmsSyntax() {
        return smsSyntax;
    }

    public void setSmsSyntax(String smsSyntax) {
        this.smsSyntax = smsSyntax;
    }

    public String getSmsPackageCode() {
        return smsPackageCode;
    }

    public void setSmsPackageCode(String smsPackageCode) {
        this.smsPackageCode = smsPackageCode;
    }

    public String getSmsLocationCode() {
        return smsLocationCode;
    }

    public void setSmsLocationCode(String smsLocationCode) {
        this.smsLocationCode = smsLocationCode;
    }

    public String getSmsStore() {
        return smsStore;
    }

    public void setSmsStore(String smsStore) {
        this.smsStore = smsStore;
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

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
