package com.fei.movieticket.entity;

import lombok.Data;

/**
 * @ClassName: MovieTicketEntity
 * @Author chengfei
 * @Date 2020/9/27 19:17
 * @Description: TODO
 **/
@Data
public class MovieTicketEntity {
    //优惠券名称
    private String Name;
    //优惠券描述
    private String Summary;
    //优惠券价格
    private Double Price;
    //优惠价id
    private Integer SpId;
    //数量
    private Integer num;

}
