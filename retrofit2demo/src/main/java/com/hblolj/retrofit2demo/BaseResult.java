package com.hblolj.retrofit2demo;

/**
 * Created by hblolj on 2017/3/24.
 */

public class BaseResult {

    private String status;
    private String message;

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    @Override
    public String toString() {
        return "BaseResult{" +
                "status='" + status + '\'' +
                ", message='" + message + '\'' +
                '}';
    }
}
