package com.fei.movieticket.service;

import com.fei.movieticket.bo.URLBo;
import com.fei.movieticket.vo.TicketVo;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @Description: 解析URL地址
 */
public interface AnalysisTicket {
    public List<TicketVo> getTicket(URLBo urlBo);
}
