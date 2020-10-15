package com.fei.movieticket.service;

import com.fei.movieticket.bo.QueryConditionBo;
import com.fei.movieticket.vo.TicketVo;

import java.util.List;

public interface SearchData {
    List<TicketVo> goodTicket(List<TicketVo> ticketVos, QueryConditionBo queryConditionBo);
}
