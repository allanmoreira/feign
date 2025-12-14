package br.com.moreirallan.feign.properties.service;


public class ServiceItem {

    protected String key;
    protected String url;
    protected Integer statusCode;
    protected FeignConfig feign;

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public Integer getStatusCode() {
        return statusCode;
    }

    public void setStatusCode(Integer statusCode) {
        this.statusCode = statusCode;
    }

    public FeignConfig getFeign() {
        return feign;
    }

    public void setFeign(FeignConfig feign) {
        this.feign = feign;
    }
}
