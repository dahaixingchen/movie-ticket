package com.fei.movieticket.util;


/**
 * 开放接口异常码
 *
 * @author 顾小伟
 */
public enum ErrorCode {

    //系统异常 1
    QUERY_ERROR("NCA1000001",MessageConstant.QUERY_ERROR_MESSAGE),
    ADD_ERROR("NCA1000002",MessageConstant.ADD_ERROR_MESSAGE),
    UPDATE_ERROR("NCA1000003",MessageConstant.UPDATE_ERROR_MESSAGE),
    DELETE_ERROR("NCA1000004",MessageConstant.DELETE_ERROR_MESSAGE),
    DATE_FORMAT_ERROR("NCA1001002", MessageConstant.DATE_FORMAT_ERROR_MESSAGE),
    SYS_FORMAT_ERROR("NCA1000000", MessageConstant.SYS_ERROR_MESSAGE),

    //业务异常 2
    PARAM_ERROR("NCA2000001", MessageConstant.PARAM_ERROR_MESSAGE),
    ;

    private String code;

    private String message;

    ErrorCode(String code, String message) {
        this.code = code;
        this.message = message;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
