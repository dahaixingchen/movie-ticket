package com.fei.elasticsearch.data.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.fei.elasticsearch.data.bo.TicketVo;
import com.fei.elasticsearch.data.bo.URLBo;
import com.fei.elasticsearch.data.mapper.MovieTicketMapper;
import com.fei.elasticsearch.data.service.AnalysisTicket;
import com.fei.elasticsearch.data.service.MovieTicketService;
import com.fei.elasticsearch.data.util.ConnectES;
import org.elasticsearch.action.admin.indices.create.CreateIndexRequestBuilder;
import org.elasticsearch.client.transport.TransportClient;
import org.elasticsearch.common.xcontent.XContentBuilder;
import org.elasticsearch.common.xcontent.XContentFactory;
import org.elasticsearch.common.xcontent.XContentType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * @ClassName: MovieTicketServiceImpl
 * @Author chengfei
 * @Date 2020/9/27 19:14
 * @Description: TODO
 **/
@Service
public class MovieTicketServiceImpl implements MovieTicketService {

    private TransportClient client = ConnectES.connect();
    @Resource
    private MovieTicketMapper movieTicketMapper;

    @Autowired
    private AnalysisTicket analysisTicketHTML;

    @Autowired
    private AnalysisTicket analysisTicketHtmlNew;

    @Autowired
    private AnalysisTicket analysisTicketPort;

    @Override
    public void disposeTicketResult() {
        List<URLBo> urls = movieTicketMapper.getUrlBo();
        List<URLBo> portUrls = movieTicketMapper.getPortUrlBo();
        List<TicketVo> ticketVos = new ArrayList<>();
        urls.forEach(urlBo->{
            List<TicketVo> ticket = analysisTicketHtmlNew.getTicket(urlBo);
            ticketVos.addAll(ticket);
        });
        portUrls.forEach(urlBo->{
            List<TicketVo> ticket = analysisTicketPort.getTicket(urlBo);
            ticketVos.addAll(ticket);
        });
        //存入elasticsearch中
        this.storeData(ticketVos);
    }

    /**
     * @Description: 存储数据
      * @param ticketVos
     * @return void
     * @date: 2020/10/23 11:15
     */
    private void storeData(List<TicketVo> ticketVos) {
        //删除索引
        try {
            client.admin().indices().prepareDelete("ticket").execute().actionGet();
        }catch (Exception e){
            e.printStackTrace();
        }
        //创建带ik分词器的索引
        this.createIndex();
        //把数据存入ES中
        int i = 0;
        for (; i < ticketVos.size(); i++) {
            String json = JSONObject.toJSONString(ticketVos.get(i));
            client.prepareIndex("ticket", "html", String.valueOf(i))
                    .setSource(json, XContentType.JSON).get();
        }
    }

    /**
     * @Description: 创建带ik分词器的索引
      * @param
     * @return void
     * @date: 2020/10/23 11:14
     */
    private void createIndex() {

        // 1:settings
        HashMap<String, Object> settings_map = new HashMap<String, Object>(2);
        // 分区的数量4
        settings_map.put("number_of_shards", 4);
        // 副本的数量1
        settings_map.put("number_of_replicas", 1);
        XContentBuilder xContentBuilder = null;
        try {
            xContentBuilder = XContentFactory.jsonBuilder().startObject()
                    .field("number_of_shards", "1")
                    .field("number_of_replicas", "1").startObject("analysis")
                    .startObject("analyzer").startObject("ik").field("tokenizer", "ik_max_word")
                    .endObject().endObject().endObject().endObject();
        } catch (IOException e) {
            e.printStackTrace();
        }

        // 2:mappings
        XContentBuilder builder = null;
        try {
            builder = XContentFactory.jsonBuilder().startObject()//
                    .field("dynamic", "true").startObject("properties")
                    // 即分词,又建立索引、不在文档中存储、
                    .startObject("name").field("type", "text").field("store", "false")
                    .field("index", "true").field("analyzer", "ik_max_word").endObject()

                    .endObject()

                    .endObject();
        } catch (IOException e) {
            e.printStackTrace();
        }

        CreateIndexRequestBuilder prepareCreate = client.admin().indices().prepareCreate("ticket");
        prepareCreate.setSettings(xContentBuilder).addMapping("html", builder).get();
    }


}
