package com.example.boottest.model;

import org.springframework.http.HttpStatus;

public class ResultModel {
    private HttpStatus status;//for client program
    private ResultState code;//for client program
    private String message;//for user
    private String developerMessage;//for developer
    private String moreInfo;//for developer
    private Object data;//for client program

    public HttpStatus getState() {
        return status;
    }

    public void setState(HttpStatus state) {
        this.status = state;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
