package com.lazyfish.codeshare.exception;

/**
 * 请求的资源id不存在异常
 */
public class RequireIdNotExistException extends RuntimeException{
    public RequireIdNotExistException(String message) {
        super(message);
    }
}
