package com.fei.elasticsearch.data.service.impl;

import com.fei.elasticsearch.data.bo.TicketVo;
import com.fei.elasticsearch.data.bo.URLBo;
import com.fei.elasticsearch.data.service.AnalysisTicket;
import com.fei.elasticsearch.data.service.URLData;
import org.apache.commons.lang.StringUtils;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
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

    @Autowired
    private URLData URLDataFromClass;

    @Autowired
    private URLDataFromTag URLDataFromTag;

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
                if (htmlParmBo.getBigDivClass2() != null){
                    Elements elementsByClass = document.getElementsByClass(htmlParmBo.getBigDivClass2());
                    elements.addAll(elementsByClass);
                }
                ticketVos = URLDataFromClass.analyseUrlData(elements,htmlParmBo);
            } else if (htmlParmBo.getBigDivTag() != null) {
                //用tag解析
                elements = document.getElementsByTag(htmlParmBo.getBigDivTag());
                ticketVos = URLDataFromTag.analyseUrlData(elements, htmlParmBo);
            }else if (htmlParmBo.getBigOtherLabel() != null) {
                //用其他的标签解析
                String[] lableType = htmlParmBo.getBigOtherLabelType().split(";");
                for (String type : lableType) {
                    Elements elements1 = document.getElementsByAttributeValue(htmlParmBo.getBigOtherLabel(), type);
                    elements.addAll(elements1);
                }
                ticketVos = URLDataFromTag.analyseUrlData(elements,htmlParmBo);
            }else {
                throw new IllegalArgumentException("解析地址失败，对应的url：" + htmlParmBo.getUrl());
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        return ticketVos;
    }
}
