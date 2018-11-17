package com.ss.rh.util;

public class MyMsg {
    private boolean success;
    private String msg;
    private Object object;

    public MyMsg(boolean success, String msg) {
        this.success = success;
        this.msg = msg;
    }

    public MyMsg(boolean success, String msg, Object object) {
        this.success = success;
        this.msg = msg;
        this.object = object;
    }

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public Object getObject() {
        return object;
    }

    public void setObject(Object object) {
        this.object = object;
    }
}
