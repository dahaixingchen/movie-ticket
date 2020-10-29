package com.fei.elasticsearch.data.service.impl;

import com.fei.elasticsearch.data.bo.TicketVo;
import com.fei.elasticsearch.data.bo.URLBo;
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
        Elements titleElsments = new Elements();
        Elements priceElements = new Elements();
        Elements numElements = new Elements();
        Elements descElements = new Elements();
        Elements buyUrlElements = new Elements();
        for (Element element : elements) {
            TicketVo ticketVo = new TicketVo();
            //实例化tickets
            ticketVo.setUrl(htmlParmBo.getUrl());
            if (htmlParmBo.getTitleClass() != null){
                titleElsments = element.getElementsByClass(htmlParmBo.getTitleClass());
                ticketVo.setName(titleElsments.get(0).text());
            }
            if (htmlParmBo.getPriceClass() != null){
                priceElements = element.getElementsByClass(htmlParmBo.getPriceClass());
                Matcher priceMatcher = NumberUtil.getMatcher(priceElements.get(0));
                if (priceMatcher.find()) {
                    ticketVo.setPrice(Double.valueOf(priceMatcher.group(1)));
                }
            }
            if (htmlParmBo.getNumClass() != null){
                numElements = element.getElementsByClass(htmlParmBo.getNumClass());
                Matcher numMatcher = NumberUtil.getMatcher(numElements.get(0));
                if (numMatcher.find()) {
                    ticketVo.setNum(Integer.valueOf(numMatcher.group(1)));
                }
            }
            if (htmlParmBo.getDescClass() != null){
                descElements = element.getElementsByClass(htmlParmBo.getDescClass());
                ticketVo.setDescribe(descElements.get(0).text());
            }
            if (htmlParmBo.getBuyRule() != null){
                buyUrlElements = element.select("a");
                ticketVo.setBuyRule(htmlParmBo.getBuyRule() + buyUrlElements.get(0).attr( "href"));
            }
            ticketVos.add(ticketVo);
        }
        return ticketVos;
    }
}
