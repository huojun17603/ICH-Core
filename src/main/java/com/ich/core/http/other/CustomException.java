package com.ich.core.http.other;

/**
 * 自定义的错误
 * 可用于判别是系统错误，还是人为抛出
 */
public class CustomException extends RuntimeException {

    /**
     * 错误编码：为自己的系统自定一套错误编码，可以在处理错误时分别处理
     * */
    private int code;

    public  CustomException(int code,String msg){
        super(msg);
        this.code = code;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }
}
