package com.fei.movieticket.bo;

import lombok.Data;
import sun.management.Agent;

/**
 * @ClassName: URLBo
 * @Author chengfei
 * @Date 2020/10/1 22:36
 * @Description: TODO
 **/
@Data
public class URLBo {
    private String url;
    private String bigDivClass;
    private String standbyClass1;
    private String standbyClass2;
    private String titleClass;
    private String numClass;
    private String priceClass;
    private String descClass;
    private String bigDivTag;
    private String standbyTag1;
    private String standbyTag2;
    private String titleTag;
    private String numTag;
    private String priceTag;
    private String descTag;

    private String referer;
    private String userAgent;
    private String cookie;
}
