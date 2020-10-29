package com.fei.elasticsearch.data.service;

import com.fei.elasticsearch.data.bo.TicketVo;
import com.fei.elasticsearch.data.bo.URLBo;
import org.jsoup.select.Elements;

import java.util.List;

public interface URLData {

    /**
     * @Description:
     * @param elements
     * @param htmlParmBo
     * @return void
     * @date: 2020/10/28 19:09
     */
    public List<TicketVo> analyseUrlData( Elements elements, URLBo htmlParmBo);
}
