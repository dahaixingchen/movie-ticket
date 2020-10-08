package com.fei.movieticket.vo;

import lombok.Data;

/**
 * @ClassName: TicketVo
 * @Author chengfei
 * @Date 2020/10/1 22:14
 * @Description: TODO
 **/
@Data
public class TicketVo {
    //优惠券名称
    private String name;
    //优惠券描述
    private String describe;
    //优惠券价格
    private Double price;
    //数量
    private Integer num;
    //优惠券URL
    private String url;

    public TicketVo(){
        this.price = 0d;
        this.num = 9999;
    }
}
