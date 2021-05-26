package com.lazyfish.codeshare.utils;

public class ResultBuild {
    int status = -1;
    private Object data;
    private String message = "哦豁，调用成功！";


    public ResultBuild(int status, Object data) {
        this.status = status;
        this.data = data;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
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
