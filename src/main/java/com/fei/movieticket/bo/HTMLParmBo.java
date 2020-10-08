package com.fei.movieticket.bo;

import lombok.Data;

/**
 * @ClassName: HTMLParmBo
 * @Author chengfei
 * @Date 2020/10/7 10:40
 * @Description: TODO
 **/
@Data
public class HTMLParmBo {
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

    public HTMLParmBo() {
    }
    public HTMLParmBo(String url, String bigDivClass, String standbyClass1, String standbyClass2, String titleClass, String numClass, String priceClass, String descClass, String bigDivTag, String standbyTag1, String standbyTag2, String titleTag, String numTag, String priceTag, String descTag) {
        this.url = url;
        this.bigDivClass = bigDivClass;
        this.standbyClass1 = standbyClass1;
        this.standbyClass2 = standbyClass2;
        this.titleClass = titleClass;
        this.numClass = numClass;
        this.priceClass = priceClass;
        this.descClass = descClass;
        this.bigDivTag = bigDivTag;
        this.standbyTag1 = standbyTag1;
        this.standbyTag2 = standbyTag2;
        this.titleTag = titleTag;
        this.numTag = numTag;
        this.priceTag = priceTag;
        this.descTag = descTag;
    }
}
