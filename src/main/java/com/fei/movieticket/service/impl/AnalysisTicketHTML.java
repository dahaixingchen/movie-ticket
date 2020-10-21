package com.fei.movieticket.service.impl;

import com.fei.movieticket.bo.URLBo;
import com.fei.movieticket.service.AnalysisTicket;
import com.fei.movieticket.vo.TicketVo;
import org.apache.commons.lang.StringUtils;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @ClassName: AnalysisTicketHTML
 * @Author chengfei
 * @Date 2020/10/6 12:31
 * @Description: TODO
 **/
@Service
public class AnalysisTicketHTML implements AnalysisTicket {
    @Override
    public List<TicketVo> getTicket(URLBo urlBo) {
        if (StringUtils.isEmpty(urlBo.getUrl())) {
            throw new IllegalArgumentException("访问地址url不能为空");
        }
        return this.parseCon(urlBo);
    }


    private List<TicketVo> parseCon(URLBo htmlParmBo) {
        List<TicketVo> ticketVos = new ArrayList<>();
        try {
            Document document = Jsoup.connect(htmlParmBo.getUrl()).get();
            //默认最外层的最多一步就能解析到位
            if (htmlParmBo.getBigDivClass() != null) {
                //用class解析
                Elements titles = document.getElementsByAttributeValue("class", htmlParmBo.getBigDivClass());
                if (titles.size() == 1) {
                    //如果titles为1说明接下能一步解析到位
                    this.titleDetail(ticketVos, titles, htmlParmBo);
                } else {
                    for (int i = 0; i < titles.size(); i++) {
                        TicketVo ticketVo = new TicketVo();
                        //解析具体的影票属性
                        this.titleDetail(ticketVo, htmlParmBo, titles, i);
                        ticketVos.add(ticketVo);

                    }
                }
            } else if (htmlParmBo.getBigDivTag() != null) {
                //用tag解析
                Elements titles = document.getElementsByTag(htmlParmBo.getBigDivTag());
                for (int i = 0; i < titles.size(); i++) {
                    TicketVo ticketVo = new TicketVo();
                    //解析具体的影票属性
                    this.titleDetail(ticketVo, htmlParmBo, titles, i);
                    ticketVos.add(ticketVo);
                }
            } else {
                //直接能一步解析到位
//                this.titleDetail(ticketVos, document, htmlParmBo);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return ticketVos;
    }

    /**
     *
     * @param elements
     * @param htmlParmBo
     * @return void
     * @Description: 具体的影票属性解析，只有一个元素(一步解析得到)
     * @date: 2020/10/8 17:26
     */
    private void titleDetail(List<TicketVo> ticketVos, Elements elements, URLBo htmlParmBo) {
//        Elements aClass = document.getElementsByAttributeValue("class", htmlParmBo.getBigDivClass());
        Elements titles = new Elements();
        Elements num = new Elements();
        Elements price = new Elements();
        Elements desc = new Elements();
        //按照class解析
        if (htmlParmBo.getTitleClass() != null) {
            titles = elements.get(0).getElementsByAttributeValue("class", htmlParmBo.getTitleClass());
        }
        if (htmlParmBo.getNumClass() != null) {
            num = elements.get(0).getElementsByAttributeValue("class", htmlParmBo.getNumClass());
        }
        if (htmlParmBo.getPriceClass() != null) {
            price = elements.get(0).getElementsByAttributeValue("class", htmlParmBo.getPriceClass());
        }
        if (htmlParmBo.getDescClass() != null) {
            desc = elements.get(0).getElementsByAttributeValue("class", htmlParmBo.getDescClass());
        }
        //按照tag解析
        if (htmlParmBo.getTitleTag() != null) {
            titles = elements.get(0).getElementsByTag(htmlParmBo.getTitleTag());
        }
        if (htmlParmBo.getNumTag() != null) {
            num = elements.get(0).getElementsByTag(htmlParmBo.getNumTag());
        }
        if (htmlParmBo.getPriceTag() != null) {
            price = elements.get(0).getElementsByTag(htmlParmBo.getPriceTag());
        }
        if (htmlParmBo.getDescTag() != null) {
            desc = elements.get(0).getElementsByTag(htmlParmBo.getDescTag());
        }
        if (ticketVos.size() == 0 && titles.size() == price.size() && titles.size() == num.size() && titles.size() == desc.size()) {
            for (int i = 0; i < titles.size(); i++) {
                TicketVo ticketVo = new TicketVo();
                ticketVo.setUrl(htmlParmBo.getUrl());
                ticketVo.setName(titles.get(i).text());
                Matcher priceMatcher = this.getMatcher(price.get(i));
                if (priceMatcher.find()) {
                    ticketVo.setPrice(Double.valueOf(priceMatcher.group(1)));
                }
                Matcher numMatcher = this.getMatcher(num.get(i));
                if (numMatcher.find()) {
                    ticketVo.setNum(Integer.valueOf(numMatcher.group(1)));
                }
                ticketVo.setDescribe(desc.get(i).text());
            }
        }

        if (ticketVos.size() == 0 && titles.size() == price.size() && titles.size() == num.size()) {
            for (int i = 0; i < titles.size(); i++) {
                TicketVo ticketVo = new TicketVo();
                ticketVo.setUrl(htmlParmBo.getUrl());
                ticketVo.setName(titles.get(i).text());
                Matcher priceMatcher = this.getMatcher(price.get(i));
                if (priceMatcher.find()) {
                    ticketVo.setPrice(Double.valueOf(priceMatcher.group(1)));
                }
                Matcher numMatcher = this.getMatcher(num.get(i));
                if (numMatcher.find()) {
                    ticketVo.setNum(Integer.valueOf(numMatcher.group(1)));
                }
                ticketVos.add(ticketVo);
            }
        }

        if (ticketVos.size() == 0 && titles.size() == price.size()) {
            for (int i = 0; i < titles.size(); i++) {
                TicketVo ticketVo = new TicketVo();
                ticketVo.setUrl(htmlParmBo.getUrl());
                ticketVo.setName(titles.get(i).text());
                Matcher matcher = this.getMatcher(price.get(i));
                if (matcher.find()) {
                    ticketVo.setPrice(Double.valueOf(matcher.group(1)));
                }
                ticketVos.add(ticketVo);
            }
        }
    }

    //过滤除数字外的其他字符
    private Matcher getMatcher(Element element) {
        String regex = "(\\d+(\\.\\d+)?)";
        Pattern pattern = Pattern.compile(regex);
        return pattern.matcher(element.text());
    }

    /**
     * @param htmlParmBo
     * @param i
     * @return void
     * @Description: 具体的影票属性解析(需要两步解析得到)
     * @date: 2020/10/8 17:17
     */
    private void titleDetail(TicketVo ticketVo, URLBo htmlParmBo, Elements elements, int i) {
        Elements titles = new Elements();
        Elements num = new Elements();
        Elements price = new Elements();
        Elements desc = new Elements();
        //按照class解析
        if (htmlParmBo.getTitleClass() != null) {
            titles = elements.get(i).getElementsByAttributeValue("class", htmlParmBo.getTitleClass());
        }
        if (htmlParmBo.getNumClass() != null) {
            num = elements.get(i).getElementsByAttributeValue("class", htmlParmBo.getNumClass());
        }
        if (htmlParmBo.getPriceClass() != null) {
            price = elements.get(i).getElementsByAttributeValue("class", htmlParmBo.getPriceClass());
        }
        if (htmlParmBo.getDescClass() != null) {
            desc = elements.get(i).getElementsByAttributeValue("class", htmlParmBo.getDescClass());
        }
        //按照tag解析
        if (htmlParmBo.getTitleTag() != null) {
            titles = elements.get(i).getElementsByTag(htmlParmBo.getTitleTag());
        }
        if (htmlParmBo.getNumTag() != null) {
            num = elements.get(i).getElementsByTag(htmlParmBo.getNumTag());
        }
        if (htmlParmBo.getPriceTag() != null) {
            price = elements.get(i).getElementsByTag(htmlParmBo.getPriceTag());
        }
        if (htmlParmBo.getDescTag() != null) {
            desc = elements.get(i).getElementsByTag(htmlParmBo.getDescTag());
        }

        //影票所有的属性都用一个tag标签
        if (htmlParmBo.getTitleTag() != null && htmlParmBo.getPriceTag() == null) {
            for (int i1 = 0; i1 < titles.size(); i1++) {
                if (i1 == 0) {
                    //第一个默认肯定是标题
                    ticketVo.setName(titles.get(0).text());
                }
                Matcher matcher = this.getMatcher(titles.get(i1));
                if (matcher.find()) {
                    if (matcher.group(1).split("\\.").length > 1) {
                        //带有小数点，是价格
                        ticketVo.setPrice(Double.valueOf(matcher.group(1)));
                    } else {
                        //是数量，或是其他的
                        ticketVo.setNum(Integer.valueOf(matcher.group(1)));
                    }
                }
            }
        }
        //影票所有的属性都用一个class标记
        if (htmlParmBo.getTitleClass() != null && htmlParmBo.getPriceClass() == null) {
            for (int i1 = 0; i1 < titles.size(); i1++) {
                if (i1 == 0) {
                    //第一个默认肯定是标题
                    ticketVo.setName(titles.get(0).text());
                }
                Matcher matcher = this.getMatcher(titles.get(i1));
                if (matcher.find()) {
                    if (matcher.group(1).split("\\.").length > 1) {
                        //带有小数点，是价格
                        ticketVo.setPrice(Double.valueOf(matcher.group(1)));
                    } else {
                        //是数量，或是其他的
                        ticketVo.setNum(Integer.valueOf(matcher.group(1)));
                    }
                }
            }
        }
        if (titles.size() == price.size()) {
            for (int j = 0; j < titles.size(); j++) {
                ticketVo.setName(titles.get(j).text());
                Matcher matcher = this.getMatcher(price.get(j));
                if (matcher.find()) {
                    ticketVo.setPrice(Double.valueOf(matcher.group(1)));
                }
            }
        }
        if (titles.size() == price.size() && titles.size() == num.size()) {
            for (int j = 0; j < titles.size(); j++) {
                ticketVo.setName(titles.get(j).text());
                Matcher priceMatcher = this.getMatcher(price.get(j));
                if (priceMatcher.find()) {
                    ticketVo.setPrice(Double.valueOf(priceMatcher.group(1)));
                }
                Matcher numMatcher = this.getMatcher(num.get(j));
                if (numMatcher.find()) {
                    ticketVo.setNum(Integer.valueOf(numMatcher.group(1)));
                }
            }
        }
        if (titles.size() == price.size() && titles.size() == num.size() && titles.size() == desc.size()) {
            for (int j = 0; j < titles.size(); j++) {
                ticketVo.setName(titles.get(j).text());
                Matcher priceMatcher = this.getMatcher(price.get(j));
                if (priceMatcher.find()) {
                    ticketVo.setPrice(Double.parseDouble(priceMatcher.group(1)));
                }
                Matcher numMatcher = this.getMatcher(num.get(j));
                if (numMatcher.find()) {
                    ticketVo.setNum(Integer.valueOf(numMatcher.group(1)));
                }
                ticketVo.setDescribe(desc.get(j).text());
            }
        }
        ticketVo.setUrl(htmlParmBo.getUrl());
    }
}
