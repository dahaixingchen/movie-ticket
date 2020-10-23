package com.fei.search.ticket.service;


import com.fei.search.ticket.bo.QueryConditionBo;
import com.fei.search.ticket.vo.TicketVo;

import java.util.List;

public interface SearchData {
    List<TicketVo> goodTicket(List<TicketVo> ticketVos, QueryConditionBo queryConditionBo);
}
