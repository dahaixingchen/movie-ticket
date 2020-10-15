package com.fei.movieticket.util;

/**
  * @Date: 2020/6/22 20:06
  * @Description: 响应体
  **/
public class ApiMessage<T> {

    public static final int SUCCESS = 200;

    public static final int ERROR = 400;

    private String timestamp;

    private int status;

    private String message;

    private String errorCode;

    private Integer dataSize;

    public Integer getDataSize() {
        return dataSize;
    }

    public void setDataSize(Integer dataSize) {
        this.dataSize = dataSize;
    }

    private T data;


    public ApiMessage() {
        super();
    }

    public ApiMessage(String timestamp, int status, String message) {
        this.timestamp = timestamp;
        this.status = status;
        this.message = message;
    }

    public ApiMessage(String timestamp, int status, String message, String errorCode) {
        this.timestamp = timestamp;
        this.status = status;
        this.message = message;
        this.errorCode = errorCode;
    }

    public ApiMessage(String timestamp, int status, String message, T data) {
        this.timestamp = timestamp;
        this.status = status;
        this.message = message;
        this.data = data;
    }

    public ApiMessage(String timestamp, int status, String message, String errorCode, T data) {
        this.timestamp = timestamp;
        this.status = status;
        this.message = message;
        this.errorCode = errorCode;
        this.data = data;
    }

    public ApiMessage(String timestamp, int status, String message, Integer dataSize, T data) {
        this.timestamp = timestamp;
        this.status = status;
        this.message = message;
        this.dataSize = dataSize;
        this.data = data;
    }

//    public static ApiMessage success(String message) {
//        return new ApiMessage(DateTimeUtil.initCurrentTime(), SUCCESS, message);
//    }
//
//    public static ApiMessage success(String message, Object data) {
//        return new ApiMessage(DateTimeUtil.initCurrentTime(), SUCCESS, message, data);
//    }

    public static ApiMessage success(String message,Integer dataSize, Object data) {
        return new ApiMessage(DateTimeUtil.initCurrentTime(), SUCCESS, message,dataSize, data);
    }

    public static ApiMessage errorParam(String message) {
        return new ApiMessage(DateTimeUtil.initCurrentTime(), ERROR, message, ErrorCode.PARAM_ERROR.getCode());
    }

    public static ApiMessage error(ErrorCode errorCode) {
        return new ApiMessage(DateTimeUtil.initCurrentTime(), ERROR, errorCode.getMessage(), errorCode.getCode());
    }

    public static ApiMessage error(String message) {
        return new ApiMessage(DateTimeUtil.initCurrentTime(), ERROR, message);
    }

    public String getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(String timestamp) {
        this.timestamp = timestamp;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getErrorCode() {
        return errorCode;
    }

    public void setErrorCode(String errorCode) {
        this.errorCode = errorCode;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }
}
