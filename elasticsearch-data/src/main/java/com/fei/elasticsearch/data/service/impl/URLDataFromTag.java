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

/**
 * @ClassName: URLDataFromTag
 * @Author chengfei
 * @Date 2020/10/29 15:18
 * @Description: TODO
 **/
@Service
public class URLDataFromTag implements URLData {
    @Override
    public List<TicketVo> analyseUrlData(Elements elements, URLBo htmlParmBo) {
        List<TicketVo> ticketVos = new ArrayList<>();
        Elements titleElsments = new Elements();
        Elements priceElements = new Elements();
        Elements numElements = new Elements();
        Elements descElements = new Elements();
        Elements buyUrlElements = new Elements();
        for (Element element : elements) {
            TicketVo ticketVo = new TicketVo();
            //如果价格，数量同事都为null，就认定它是一个标签（class，功能没有实现）得到所有的数据的
            if (htmlParmBo.getTitleTag() != null && htmlParmBo.getPriceTag() == null && htmlParmBo.getNumTag() == null) {
                titleElsments = element.getElementsByTag(htmlParmBo.getTitleTag());
                for (int i1 = 0; i1 < titleElsments.size(); i1++) {
                    if (i1 == 0) {
                        //第一个默认肯定是标题
                        ticketVo.setName(titleElsments.get(0).text());
                    }
                    Matcher matcher = NumberUtil.getMatcher(titleElsments.get(i1));
                    if (matcher.find()) {
                        if (matcher.group(1).split("\\.").length > 1) {
                            //带有小数点，是价格
                            ticketVo.setPrice(Double.valueOf(matcher.group(1)));
                        } else {
                            //是数量，或是其他的
                            ticketVo.setNum(Integer.valueOf(matcher.group(1)));
                        }
                    }else if (htmlParmBo.getBuyRule() != null){
                        //说明不是数字
                        //判断下购买地址是否正确
                        if (htmlParmBo.getBuyRule().equals(ticketVo.getBuyRule()) || ticketVo.getBuyRule() == null){
                            //ticketVo.getBuyRule() 说明第一次初始化，前面的条件说明a标签解析错误
                            ticketVo.setBuyRule(htmlParmBo.getBuyRule() + titleElsments.get(i1).select("a").attr("href"));
                        }
                    }
                }
            }else {
                //多个class（标签，没有实现）分别得到price和num或其他字段
                ticketVo = AnalyseData.analyseClassOrTag(htmlParmBo, element);
            }
            ticketVos.add(ticketVo);
        }
        return ticketVos;
    }

}
