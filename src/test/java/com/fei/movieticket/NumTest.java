package com.fei.movieticket;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


/**
 * @ClassName: NumTest
 * @Author chengfei
 * @Date 2020/10/7 10:56
 * @Description: TODO
 **/
public class NumTest {


    /**
     * 2016.10.25
     */

    public static void main(String[] args) {
        String a="love23.63next234csdn3423javaeye";
        String regEx="[^0-9]";
        regEx="(\\d+(\\.\\d+)?)";
//        (\d+(\.\d+)?)
        Pattern p = Pattern.compile(regEx);
        Matcher m = p.matcher(a);
        StringBuilder stringBuilder = new StringBuilder();
        if (m.find());
        String group = m.group(1);
        System.out.println(group);
        while (m.find()){
            stringBuilder.append(m.group(1)+",");
        }
        System.out.println(stringBuilder.toString());
    }

}
