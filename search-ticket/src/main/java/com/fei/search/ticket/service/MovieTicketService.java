package com.fei.search.ticket.service;


import com.fei.search.ticket.bo.QueryConditionBo;
import com.fei.search.ticket.vo.TicketVo;

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

    Boolean checkVip(String userId);
}
