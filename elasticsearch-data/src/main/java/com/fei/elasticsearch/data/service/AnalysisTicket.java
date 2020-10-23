package com.fei.elasticsearch.data.service;



import com.fei.elasticsearch.data.bo.TicketVo;
import com.fei.elasticsearch.data.bo.URLBo;

import java.util.List;

/**
 * @Description: 解析URL地址
 */
public interface AnalysisTicket {
    public List<TicketVo> getTicket(URLBo urlBo);
}
