package com.fei.elasticsearch.data.bo;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

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
    //电影票URL
    private String url;

    //电影票URL
    private String buyRule;

    public TicketVo(){
        this.price = 0d;
        this.num = 9999;
    }
}
