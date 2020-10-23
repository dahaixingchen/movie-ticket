package com.fei.movieticket.service.impl;

import com.fei.movieticket.bo.QueryConditionBo;
import com.fei.movieticket.bo.URLBo;
import com.fei.movieticket.mapper.MovieTicketMapper;
import com.fei.movieticket.service.AnalysisTicket;
import com.fei.movieticket.service.MovieTicketService;
import com.fei.movieticket.service.SearchData;
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

    @Resource
    private MovieTicketMapper movieTicketMapper;

    @Autowired
    private AnalysisTicketHTML analysisTicketHTML;

    @Autowired
    private AnalysisTicketPort analysisTicketPort;

    @Autowired
    private SearchData searchData;

    @Override
    public List<TicketVo> disposeTicketResult(QueryConditionBo queryConditionBo) {
        List<URLBo> urls = movieTicketMapper.getUrlBo();
        List<URLBo> portUrls = movieTicketMapper.getPortUrlBo();
        List<TicketVo> ticketVos = new ArrayList<>();
        urls.forEach(urlBo->{
            List<TicketVo> ticket = analysisTicketHTML.getTicket(urlBo);
            ticketVos.addAll(ticket);
        });
        portUrls.forEach(urlBo->{
            List<TicketVo> ticket = analysisTicketPort.getTicket(urlBo);
            ticketVos.addAll(ticket);
        });
        return searchData.goodTicket(ticketVos,queryConditionBo);
    }


}
