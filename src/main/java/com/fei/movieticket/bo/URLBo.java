package com.fei.movieticket.bo;

import lombok.Data;

/**
 * @ClassName: URLBo
 * @Author chengfei
 * @Date 2020/10/1 22:36
 * @Description: TODO
 **/
@Data
public class URLBo {
    //URL地址
    private String url;

    //URL解释的方式，0：接口解析，1：还是HTML代码解析
    private Integer urlType;

    //包裹影票优惠券的div对应的class
    private String bigDivClass;

    //包裹影票优惠券内容的html标签
    private String contentTarget;

    //包裹影票优惠券标题的html，class标签
    private String titleClass;

    //包裹影票优惠券价格的html，class标签
    private String priceClass;

    //包裹影票优惠券描述的html，class标签
    private String descClass;

    //包裹影票优惠券数量的html，class标签
    private String numClass;
}
