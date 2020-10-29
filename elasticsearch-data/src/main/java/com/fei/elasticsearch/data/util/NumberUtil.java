package com.fei.elasticsearch.data.util;

import org.jsoup.nodes.Element;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @ClassName: NumberUtil
 * @Author chengfei
 * @Date 2020/10/29 15:45
 * @Description: TODO
 **/
public class NumberUtil {
    //过滤除数字外的其他字符
    public static Matcher getMatcher(Element element) {
        String regex = "(\\d+(\\.\\d+)?)";
        Pattern pattern = Pattern.compile(regex);
        return pattern.matcher(element.text());
    }
}
