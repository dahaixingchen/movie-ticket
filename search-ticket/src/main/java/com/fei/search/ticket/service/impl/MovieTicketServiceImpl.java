package com.fei.search.ticket.service.impl;

import com.fei.search.ticket.bo.QueryConditionBo;
import com.fei.search.ticket.service.MovieTicketService;
import com.fei.search.ticket.service.SearchData;
import com.fei.search.ticket.vo.TicketVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

/**
 * @ClassName: MovieTicketServiceImpl
 * @Author chengfei
 * @Date 2020/9/27 19:14
 * @Description: TODO
 **/
@Service
public class MovieTicketServiceImpl implements MovieTicketService {

    @Autowired
    private SearchData searchData;

    @Override
    public List<TicketVo> disposeTicketResult(QueryConditionBo queryConditionBo) {
        List<TicketVo> ticketVos = new ArrayList<>();
        return searchData.goodTicket(ticketVos,queryConditionBo);
    }


}
