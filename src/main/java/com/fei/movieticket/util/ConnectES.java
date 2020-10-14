package com.fei.movieticket.util;

import org.elasticsearch.client.transport.TransportClient;
import org.elasticsearch.common.settings.Settings;
import org.elasticsearch.common.transport.TransportAddress;
import org.elasticsearch.transport.client.PreBuiltTransportClient;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.net.InetAddress;
import java.net.UnknownHostException;

/**
 * @ClassName: ConnectES
 * @Author chengfei
 * @Date 2020/10/14 14:30
 * @Description: TODO
 **/
public class ConnectES {
    static Logger logger = LoggerFactory.getLogger(ConnectES.class);
    public static TransportClient connect(){

        Settings settings = Settings.builder().put("cluster.name", "movie-title").build();
        TransportClient client = null;
        try {
            client = new PreBuiltTransportClient(settings)
                    .addTransportAddress(new TransportAddress(InetAddress.getByName("47.112.115.39"), 9300));

        } catch (UnknownHostException e) {
            logger.error("连接elasticsearch失败，请检查ES启动是否正常");
            e.printStackTrace();
        }
        return client;
    }
}
