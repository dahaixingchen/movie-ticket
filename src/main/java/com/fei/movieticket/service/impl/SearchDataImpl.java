package com.fei.movieticket.service.impl;

import com.fei.movieticket.service.SearchData;
import com.fei.movieticket.util.ConnectES;
import com.fei.movieticket.vo.TicketVo;
import org.elasticsearch.client.transport.TransportClient;
import org.elasticsearch.common.settings.Settings;
import org.elasticsearch.common.transport.TransportAddress;
import org.elasticsearch.common.xcontent.XContentType;
import org.elasticsearch.transport.client.PreBuiltTransportClient;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.List;

/**
 * @ClassName: SearchDataServiceImpl
 * @Author chengfei
 * @Date 2020/10/14 14:18
 * @Description: TODO
 **/
@Service
public class SearchDataImpl implements SearchData {

    private  Logger logger = LoggerFactory.getLogger(SearchData.class);
    private TransportClient client = ConnectES.connect();


    @Override
    public List<TicketVo> goodTicket(List<TicketVo> ticketVos) {
        //把数据存入ES中
        for (int i = 0; i < ticketVos.size(); i++) {
            client.prepareIndex("ticket","html",String.valueOf(i))
                    .setSource(ticketVos.get(i), XContentType.JSON).get();
        }
        //查询数据

        return null;
    }
}
