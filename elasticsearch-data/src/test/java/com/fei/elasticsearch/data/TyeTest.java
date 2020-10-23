package com.fei.elasticsearch.data;

import org.junit.jupiter.api.Test;

/**
 * @ClassName: TyeTest
 * @Author chengfei
 * @Date 2020/10/23 13:02
 * @Description: TODO
 **/
public class TyeTest {
    @Test
    public void test(){
        try {
            int l = 1/0;
        }catch (Exception e){
            e.printStackTrace();
        }
        System.out.println("之后执行大法师");
    }
}
