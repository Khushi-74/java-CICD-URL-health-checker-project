package com.khushi.url_health_checker.model;


public class HealthCheckResponse {

    private String url;
    private int statusCode;
    private long responseTimeMs;
    private String status;

    public HealthCheckResponse() {
    }

    public HealthCheckResponse(String url,
                               int statusCode,
                               long responseTimeMs,
                               String status) {
        this.url = url;
        this.statusCode = statusCode;
        this.responseTimeMs = responseTimeMs;
        this.status = status;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public int getStatusCode() {
        return statusCode;
    }

    public void setStatusCode(int statusCode) {
        this.statusCode = statusCode;
    }

    public long getResponseTimeMs() {
        return responseTimeMs;
    }

    public void setResponseTimeMs(long responseTimeMs) {
        this.responseTimeMs = responseTimeMs;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}