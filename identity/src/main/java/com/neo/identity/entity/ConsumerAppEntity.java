package com.neo.identity.entity;


import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "IDN_OAUTH_CONSUMER_APPS")
public class ConsumerAppEntity {

    @Id
    @Column(name = "ID")
    private Long id;

    @Column(name = "CONSUMER_KEY")
    private String consumerKey;

    @Column(name = "CONSUMER_SECRET")
    private String consumerSecret;

    @Column(name = "USERNAME")
    private String username;

    @Column(name = "TENANT_ID")
    private Long tenantId;

    @Column(name = "USER_DOMAIN")
    private String userDomain;

    @Column(name = "APP_NAME")
    private String appName;

    @Column(name = "OAUTH_VERSION")
    private String oauthVersion;

    @Column(name = "CALLBACK_URL")
    private String callbackUrl;

    @Column(name = "GRANT_TYPES")
    private String grantTypes;

    @Column(name = "PKCE_MANDATORY")
    private String pkceMandatory;

    @Column(name = "APP_STATE")
    private String appState;

    @Column(name = "USER_ACCESS_TOKEN_EXPIRE_TIME")
    private Long userAccessTokenExpireTime;

    @Column(name = "APP_ACCESS_TOKEN_EXPIRE_TIME")
    private Long appAccessTokenExpireTime;

    @Column(name = "REFRESH_TOKEN_EXPIRE_TIME")
    private Long refreshTokenExpireTime;

    @Column(name = "ID_TOKEN_EXPIRE_TIME")
    private Long idTokenExpireTime;


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getConsumerKey() {
        return consumerKey;
    }

    public void setConsumerKey(String consumerKey) {
        this.consumerKey = consumerKey;
    }

    public String getConsumerSecret() {
        return consumerSecret;
    }

    public void setConsumerSecret(String consumerSecret) {
        this.consumerSecret = consumerSecret;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public Long getTenantId() {
        return tenantId;
    }

    public void setTenantId(Long tenantId) {
        this.tenantId = tenantId;
    }

    public String getUserDomain() {
        return userDomain;
    }

    public void setUserDomain(String userDomain) {
        this.userDomain = userDomain;
    }

    public String getAppName() {
        return appName;
    }

    public void setAppName(String appName) {
        this.appName = appName;
    }

    public String getOauthVersion() {
        return oauthVersion;
    }

    public void setOauthVersion(String oauthVersion) {
        this.oauthVersion = oauthVersion;
    }

    public String getCallbackUrl() {
        return callbackUrl;
    }

    public void setCallbackUrl(String callbackUrl) {
        this.callbackUrl = callbackUrl;
    }

    public String getGrantTypes() {
        return grantTypes;
    }

    public void setGrantTypes(String grantTypes) {
        this.grantTypes = grantTypes;
    }

    public String getPkceMandatory() {
        return pkceMandatory;
    }

    public void setPkceMandatory(String pkceMandatory) {
        this.pkceMandatory = pkceMandatory;
    }

    public String getAppState() {
        return appState;
    }

    public void setAppState(String appState) {
        this.appState = appState;
    }

    public Long getUserAccessTokenExpireTime() {
        return userAccessTokenExpireTime;
    }

    public void setUserAccessTokenExpireTime(Long userAccessTokenExpireTime) {
        this.userAccessTokenExpireTime = userAccessTokenExpireTime;
    }

    public Long getAppAccessTokenExpireTime() {
        return appAccessTokenExpireTime;
    }

    public void setAppAccessTokenExpireTime(Long appAccessTokenExpireTime) {
        this.appAccessTokenExpireTime = appAccessTokenExpireTime;
    }

    public Long getRefreshTokenExpireTime() {
        return refreshTokenExpireTime;
    }

    public void setRefreshTokenExpireTime(Long refreshTokenExpireTime) {
        this.refreshTokenExpireTime = refreshTokenExpireTime;
    }

    public Long getIdTokenExpireTime() {
        return idTokenExpireTime;
    }

    public void setIdTokenExpireTime(Long idTokenExpireTime) {
        this.idTokenExpireTime = idTokenExpireTime;
    }
}
