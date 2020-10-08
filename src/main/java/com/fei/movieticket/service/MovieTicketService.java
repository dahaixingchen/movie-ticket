package com.fei.movieticket.service;

import com.fei.movieticket.bo.QueryConditionBo;
import com.fei.movieticket.vo.TicketVo;

import java.util.List;

public interface MovieTicketService {

    /**
     * @Description: 处理得到所有数据
      * @param
     * @param queryConditionBo
     * @return java.util.List<com.fei.movieticket.vo.TicketVo>
     * @date: 2020/10/1 22:23
     */
    List<TicketVo> disposeTicketResult(QueryConditionBo queryConditionBo);
}
