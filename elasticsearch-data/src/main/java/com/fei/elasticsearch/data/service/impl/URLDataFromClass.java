package com.fei.elasticsearch.data.service.impl;

import com.fei.elasticsearch.data.bo.TicketVo;
import com.fei.elasticsearch.data.bo.URLBo;
import com.fei.elasticsearch.data.common.AnalyseData;
import com.fei.elasticsearch.data.service.URLData;
import com.fei.elasticsearch.data.util.NumberUtil;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @ClassName: URLDataFromClass
 * @Author chengfei
 * @Date 2020/10/29 10:55
 * @Description: TODO
 **/
@Service
public class URLDataFromClass implements URLData {

    @Override
    public List<TicketVo> analyseUrlData( Elements elements, URLBo htmlParmBo) {
        List<TicketVo> ticketVos = new ArrayList<>();
        for (Element element : elements) {
            //实例化tickets，class的情况下
            TicketVo ticketVo = AnalyseData.analyseClassOrTag(htmlParmBo,element);
            ticketVos.add(ticketVo);
        }
        return ticketVos;
    }
}
