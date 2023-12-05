package com.shubham.project.spring_network.dto.response;

import java.io.Serializable;

public class ApiResponse<T> implements Serializable {
    private static final long serialVersionUID = 168696557625654323L;
    private int status;
    private int responseCode;
    private String message;
    private T data;

    public ApiResponse() {
    }

    public ApiResponse(int status, int responseCode, String message, T data) {
        this.status = status;
        this.responseCode = responseCode;
        this.message = message;
        this.data = data;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public int getResponseCode() {
        return responseCode;
    }

    public void setResponseCode(int responseCode) {
        this.responseCode = responseCode;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }
}
