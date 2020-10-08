package com.fei.movieticket.service.impl;

import com.fei.movieticket.bo.HTMLParmBo;
import com.fei.movieticket.bo.URLBo;
import com.fei.movieticket.entity.MovieTicketEntity;
import com.fei.movieticket.service.AnalysisTicket;
import com.fei.movieticket.vo.TicketVo;
import com.sun.org.apache.bcel.internal.generic.ARRAYLENGTH;
import org.apache.commons.lang.StringUtils;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.util.EntityUtils;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

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
public class AnalysisTicketHTML implements AnalysisTicket {
    @Override
    public List<TicketVo> getTicket(URLBo urlBo) {
        if (StringUtils.isEmpty(urlBo.getUrl())) {
            throw new IllegalArgumentException("访问地址url不能为空");
        }
        return this.parseCon(urlBo);
    }


    private List<TicketVo> parseCon(URLBo htmlParmBo) {

        // 创建httpclient对象
        HttpClient httpclient = HttpClientBuilder.create().build();
        List<TicketVo> ticketVos = new ArrayList<>();
        try {
            // 创建httpget对象
            HttpGet httpGet = new HttpGet(htmlParmBo.getUrl());
            // 执行get请求.
            HttpResponse response = httpclient.execute(httpGet);
            HttpEntity entity = response.getEntity();
            String s = EntityUtils.toString(entity);
            Document parse = Jsoup.parse(s);
            Elements elementsByClass = parse.getElementsByClass(htmlParmBo.getBigDivClass());
            elementsByClass = elementsByClass.get(0).getElementsByTag(htmlParmBo.getContentTarget());
            for (int i = 0; i < elementsByClass.size(); i++) {
                TicketVo ticketVo = new TicketVo();
                //得到优惠券的标题
                Elements titleEle = elementsByClass.get(i).getElementsByClass(htmlParmBo.getTitleClass());
                ticketVo.setName(titleEle.text());
                //得到券的描述
                Elements descEle = elementsByClass.get(i).getElementsByClass(htmlParmBo.getDescClass());
                ticketVo.setDescribe(descEle.text());

                //得到优惠券的价格
                Elements priceEle = elementsByClass.get(i).getElementsByClass(htmlParmBo.getPriceClass());
                String price = priceEle.text();
                String regex = "(\\d+(\\.\\d+)?)";
                Pattern pattern = Pattern.compile(regex);
                Matcher priceMatcher = pattern.matcher(price);
                if (priceMatcher.find()){
                    ticketVo.setPrice(Double.parseDouble(priceMatcher.group(1)));
                }
                // 得到优惠券的实际数量
                Elements numEle = elementsByClass.get(i).getElementsByClass(htmlParmBo.getNumClass());
                Matcher numMatcher = pattern.matcher(numEle.text());
                if (numMatcher.find()){
                    ticketVo.setNum(Integer.parseInt(numMatcher.group(1)));
                }
                ticketVos.add(ticketVo);
            }
        } catch (ClientProtocolException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return ticketVos;
    }
}
