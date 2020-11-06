package com.fei.elasticsearch.data.bo;

import lombok.Data;

/**
 * @ClassName: URLBo
 * @Author chengfei
 * @Date 2020/10/1 22:36
 * @Description: TODO
 **/
@Data
public class URLBo {
    private String url;
    private String buyRule;
    private String bigDivClass;
    private String bigDivClass2;
    private String bigOtherLabel;
    private String bigOtherLabelType;
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

    private String urlWeb;
    private String referer;
    private String userAgent;
    private String cookie;
    private String mark;

}
