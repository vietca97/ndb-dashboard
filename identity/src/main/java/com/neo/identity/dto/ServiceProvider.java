package com.neo.identity.dto;

public class ServiceProvider {
    private String url;
    private String name;

    public ServiceProvider() {
    }

    public ServiceProvider(String url, String name) {
        this.url = url;
        this.name = name;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "ServiceProvider{" +
                "url='" + url + '\'' +
                ", name='" + name + '\'' +
                '}';
    }
}