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
    //根据标题去查询
    private String titleQuery;

    //根据内容查询
    private String descQuery;

    //根据价格查询
    private Double priceQuery;
}
