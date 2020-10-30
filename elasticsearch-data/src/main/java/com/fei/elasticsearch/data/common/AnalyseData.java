package com.fei.elasticsearch.data.common;

import com.fei.elasticsearch.data.bo.TicketVo;
import com.fei.elasticsearch.data.bo.URLBo;
import com.fei.elasticsearch.data.util.NumberUtil;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.util.regex.Matcher;

/**
 * @ClassName: AnalyseData
 * @Author chengfei
 * @Date 2020/10/29 16:50
 * @Description: TODO
 **/
public class AnalyseData {

    /**
     * @param htmlParmBo
     * @param element
     * @return com.fei.elasticsearch.data.bo.TicketVo
     * @Description: 解析class对应的字段数据，包括了a标签的地址,url地址的设置
     * @date: 2020/10/29 16:56
     */
    public static TicketVo analyseClassOrTag(URLBo htmlParmBo, Element element) {
        TicketVo ticketVo = new TicketVo();
        Elements titleElsments;
        Elements priceElements;
        Elements numElements;
        Elements descElements;
        Elements buyUrlElements;

        ticketVo.setUrl(htmlParmBo.getUrl());
        try {

            if (htmlParmBo.getTitleClass() != null) {
                titleElsments = element.getElementsByClass(htmlParmBo.getTitleClass());
                if (titleElsments.size() >= 1) {
                    ticketVo.setName(titleElsments.get(0).text());
                }
            }
            if (htmlParmBo.getPriceClass() != null) {
                priceElements = element.getElementsByClass(htmlParmBo.getPriceClass());
                if (priceElements.size() >= 1) {
                    Matcher priceMatcher = NumberUtil.getMatcher(priceElements.get(0));
                    if (priceMatcher.find()) {
                        ticketVo.setPrice(Double.valueOf(priceMatcher.group(1)));
                    }
                }
            }
            if (htmlParmBo.getNumClass() != null) {
                numElements = element.getElementsByClass(htmlParmBo.getNumClass());
                if (numElements.size() >= 1) {
                    Matcher numMatcher = NumberUtil.getMatcher(numElements.get(0));
                    if (numMatcher.find()) {
                        ticketVo.setNum(Integer.valueOf(numMatcher.group(1)));
                    }
                }
            }
            if (htmlParmBo.getDescClass() != null) {
                descElements = element.getElementsByClass(htmlParmBo.getDescClass());
                if (descElements.size() >= 1) {

                    ticketVo.setDescribe(descElements.get(0).text());
                }
            }
            if (htmlParmBo.getBuyRule() != null) {
                buyUrlElements = element.select("a");
                if (buyUrlElements.size() >= 1) {
                    ticketVo.setBuyRule(htmlParmBo.getBuyRule() + buyUrlElements.get(0).attr("href"));
                }
            }

            //实例化tickets，tag的情况下
            if (htmlParmBo.getTitleTag() != null) {
                titleElsments = element.getElementsByTag(htmlParmBo.getTitleTag());
                if (titleElsments.size() >= 1) {

                    ticketVo.setName(titleElsments.get(0).text());
                }
            }
            if (htmlParmBo.getPriceTag() != null) {
                priceElements = element.getElementsByTag(htmlParmBo.getPriceTag());
                if (priceElements.size() >= 1) {

                    Matcher priceMatcher = NumberUtil.getMatcher(priceElements.get(0));
                    if (priceMatcher.find()) {
                        ticketVo.setPrice(Double.valueOf(priceMatcher.group(1)));
                    }
                }
            }
            if (htmlParmBo.getNumTag() != null) {
                numElements = element.getElementsByTag(htmlParmBo.getNumTag());
                if (numElements.size() >= 1) {

                    Matcher numMatcher = NumberUtil.getMatcher(numElements.get(0));
                    if (numMatcher.find()) {
                        ticketVo.setNum(Integer.valueOf(numMatcher.group(1)));
                    }
                }
            }
            if (htmlParmBo.getDescTag() != null) {
                descElements = element.getElementsByTag(htmlParmBo.getDescTag());
                if (descElements.size() >= 1) {

                    ticketVo.setDescribe(descElements.get(0).text());
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return ticketVo;
    }
}
