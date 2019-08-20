package com.fpcloud.common;

public class Result {
    public String code;
    public String message;
    public Object data;
    public boolean result;

    public Result(String code,String message,Object data,boolean result){
        this.code = code;
        this.message = message;
        this.data = data;
        this.result = result;
    }
    public Result(String code,String message,boolean result){
        this.code = code;
        this.message = message;
        this.result = result;
    }
    public Result(String code,boolean result){
        this.code = code;
        this.result = result;
    }

}
