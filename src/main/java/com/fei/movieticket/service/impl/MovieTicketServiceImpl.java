package com.fei.movieticket.service.impl;

import com.fei.movieticket.bo.QueryConditionBo;
import com.fei.movieticket.bo.URLBo;
import com.fei.movieticket.mapper.MovieTicketMapper;
import com.fei.movieticket.service.AnalysisTicket;
import com.fei.movieticket.service.MovieTicketService;
import com.fei.movieticket.vo.TicketVo;
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

    public static final String url1 = "http://res.91kami.com/Index/Index?&q=&p=1&size=200&showInStore=false";
    public static final String url2 = "http://kmg.kamigo.cn/link/R3UU341GE22O60QB";

    @Resource
    private MovieTicketMapper movieTicketMapper;

    @Autowired
    private AnalysisTicket analysisTicket;

    @Override
    public List<TicketVo> disposeTicketResult(QueryConditionBo queryConditionBo) {
        List<URLBo> urls = movieTicketMapper.getUrlBo();
        List<TicketVo> ticketVos = new ArrayList<>();
        urls.forEach(urlBo->{
            List<TicketVo> ticket = analysisTicket.getTicket(urlBo);
            ticketVos.addAll(ticket);
        });
        return ticketVos;
    }

}
