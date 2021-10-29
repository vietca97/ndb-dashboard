package com.neo.dashboard.models;

public class AuthenticationResponse {

    private final String  jwt;
    private final String  username;
    private String smsSyntax;
    private String smsPackageCode;
    private String smsLocationCode;
    private String smsStore;
    private String phone;
    private Integer status;
private String message;
private Integer code;

    public AuthenticationResponse(String jwt, String username, String smsSyntax, String smsPackageCode, String smsLocationCode, String smsStore, String phone, Integer status,Integer code,String message) {
        this.jwt = jwt;
        this.username = username;
        this.smsSyntax = smsSyntax;
        this.smsPackageCode = smsPackageCode;
        this.smsLocationCode = smsLocationCode;
        this.smsStore = smsStore;
        this.phone = phone;
        this.status = status;
this.code = code;
this.message = message;
    }
public AuthenticationResponse(String jwt ,String username , Integer code,String message) {
        this.jwt = jwt;
        this.username = username;
this.code = code;
this.message = message;
    }

 public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message= message;
    }

    public String getUsername() {
        return username;
    }

    public String getJwt() {
        return jwt;
    }

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

public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code= code;
    }

}
