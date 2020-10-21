package com.fei.movieticket.bo;

import lombok.Data;

/**
 * @ClassName: QueryConditionBo
 * @Author chengfei
 * @Date 2020/10/7 15:04
 * @Description: TODO
 **/
@Data
public class QueryConditionBo {
    //根据影院查询
    private String cinema;

    //根据平台查询
    private String platform;

    //根据地区查询
    private String area;

    //根据价格查询
    private Double price;

    //根据电影名称查询
    private String movieName;
}
