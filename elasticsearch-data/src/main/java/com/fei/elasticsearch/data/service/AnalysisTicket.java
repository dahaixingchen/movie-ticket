package com.fei.elasticsearch.data.service;



import com.fei.elasticsearch.data.bo.TicketVo;
import com.fei.elasticsearch.data.bo.URLBo;

import java.util.List;

/**
 * @Description: 解析URL地址
 */
public interface AnalysisTicket {

    /**
     * @Description: 根据url地址，解析出对应的内容
      * @param urlBo
     * @return java.util.List<com.fei.elasticsearch.data.bo.TicketVo>
     * @date: 2020/10/28 17:02
     */
    public List<TicketVo> getTicket(URLBo urlBo);
}
