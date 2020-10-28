package com.fei.elasticsearch.data.service.impl;

import com.fei.elasticsearch.data.bo.TicketVo;
import com.fei.elasticsearch.data.bo.URLBo;
import com.fei.elasticsearch.data.service.AnalysisTicket;
import org.apache.commons.lang.StringUtils;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * @ClassName: AnalysisTicketHtmlNew
 * @Author chengfei
 * @Date 2020/10/28 17:01
 * @Description: TODO
 **/
@Service
public class AnalysisTicketHtmlNew implements AnalysisTicket {
    Logger logger = LoggerFactory.getLogger(AnalysisTicketHtmlNew.class);
    @Override
    public List<TicketVo> getTicket(URLBo htmlParmBo) {

        if (StringUtils.isEmpty(htmlParmBo.getUrl())) {
            throw new IllegalArgumentException("访问地址url不能为空");
        }

        List<TicketVo> ticketVos = new ArrayList<>();

        try {
            Document document = Jsoup.connect(htmlParmBo.getUrl()).get();
            Elements elements = new Elements();

            if (htmlParmBo.getBigDivClass() != null) {
                //用class解析
                elements = document.getElementsByAttributeValue("class", htmlParmBo.getBigDivClass());
                this.classAnalyse(ticketVos,elements,htmlParmBo);
            } else if (htmlParmBo.getBigDivTag() != null) {
                //用tag解析
                elements = document.getElementsByTag(htmlParmBo.getBigDivTag());
                this.tagAnalyse(ticketVos,elements,htmlParmBo);
            }else if (htmlParmBo.getBigOtherLabel() != null) {
                //用其他的标签解析
                elements = document.getElementsByAttributeValue(htmlParmBo.getBigOtherLabel(), htmlParmBo.getBigOtherLabelType());
                this.otherLableAnalyse(ticketVos,elements,htmlParmBo);
            }else {
                throw new IllegalArgumentException("解析地址失败，对应的url：" + htmlParmBo.getUrl());
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        return null;
    }


    //根据class解析
    private void classAnalyse(List<TicketVo> ticketVos, Elements elements, URLBo htmlParmBo) {

    }

    //根据tag解析
    private void tagAnalyse(List<TicketVo> ticketVos, Elements elements, URLBo htmlParmBo) {

    }

    //用其他的标签解析
    private void otherLableAnalyse(List<TicketVo> ticketVos, Elements elements, URLBo htmlParmBo) {

    }

}
