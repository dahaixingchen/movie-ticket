package com.fei.movieticket.service.impl;

import com.fei.movieticket.bo.URLBo;
import com.fei.movieticket.service.AnalysisTicket;
import com.fei.movieticket.vo.TicketVo;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;

import java.io.IOException;
import java.util.List;

/**
 * @ClassName: AnalysisTIcketPort
 * @Author chengfei
 * @Date 2020/10/6 12:30
 * @Description: TODO
 **/
public class AnalysisTicketPort implements AnalysisTicket {

    @Override
    public List<TicketVo> getTicket(URLBo urlBo) {
        try {
            HttpClient build = HttpClientBuilder.create().build();
            HttpPost httpPost = new HttpPost(urlBo.getUrl());
//            httpPost.setHeader("");
            build.execute(httpPost);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }
}
