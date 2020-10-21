package com.fei.movieticket;

import com.alibaba.fastjson.JSONObject;
import org.junit.jupiter.api.Test;

/**
 * @ClassName: JsonTest
 * @Author chengfei
 * @Date 2020/10/21 16:31
 * @Description: TODO
 **/
public class JsonTest {
    private String s = "{\"took\":2,\"timed_out\":false,\"_shards\":{\"total\":5,\"successful\":5,\"skipped\":0,\"failed\":0}" +
            ",\"_clusters\":{\"total\":0,\"successful\":0,\"skipped\":0},\"hits\":{\"total\":500,\"max_score\":0.0,\"hits\":[]}}";
    @Test
    public void test(){
        JSONObject jsonObject = JSONObject.parseObject(s);
        JSONObject a = JSONObject.parseObject(jsonObject.getString("hits"));
        Long total = a.getLong("total");
        System.out.println(total);
    }
}
