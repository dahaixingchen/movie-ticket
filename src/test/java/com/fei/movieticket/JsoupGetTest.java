package com.fei.movieticket;

import com.fei.movieticket.bo.HTMLParmBo;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.junit.jupiter.api.Test;

import javax.sound.midi.Soundbank;
import java.io.IOException;
import java.sql.SQLOutput;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @ClassName: JsoupTEst
 * @Author chengfei
 * @Date 2020/10/8 10:00
 * @Description: TODO
 **/
public class JsoupGetTest {
    public static void main(String[] args) {
        try {
            Document document = Jsoup.connect("http://mingming.84fk.cn/").get();
            Elements titles = document.getElementsByAttributeValue("class", "elli");
            Elements price = document.getElementsByAttributeValue("class", "price");
            for (int i = 0; i < titles.size(); i++) {
                System.out.println(titles.get(i).text());
                System.out.println(price.get(i).text());
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void test1() {
        try {
            Document document = Jsoup.connect("http://fk.gofaka.cn/fkpt/index.php/Home/index/index/uid/1057981").get();
            Elements titles = document.getElementsByAttributeValue("class", "good");
            for (int i = 0; i < titles.size(); i++) {
                Elements p = titles.get(i).getElementsByTag("p");
                System.out.println(p.get(0).text());
                Elements span = titles.get(i).getElementsByTag("span");
                System.out.println(span.get(0).text());
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void test2() {
        try {
            Document document = Jsoup.connect("http://tmzdfk.com/").get();
            Elements titles = document.getElementsByTag("tr");
            for (int i = 0; i < titles.size(); i++) {
                Elements p = titles.get(i).getElementsByTag("td");
                for (int j = 0; j < p.size(); j++) {
                    System.out.println(p.get(j).text());
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void test3() {
        HTMLParmBo htmlParmBo = new HTMLParmBo("http://tmzdfk.com/", null, null, null, null, null, null, null
                , "tr", null, null, "td", null, null, null);
        htmlParmBo = new HTMLParmBo("http://fk.gofaka.cn/fkpt/index.php/Home/index/index/uid/1057981",
                "good", null, null, null, null, null, null
                , null, null, null, "p", null, "span", null);
        htmlParmBo = new HTMLParmBo("http://mingming.84fk.cn/",
                null, null, null, "elli", null, "price", null
                , null, null, null, null, null, null, null);
        try {
            Document document = Jsoup.connect(htmlParmBo.getUrl()).get();
            //默认最外层的最多一步就能解析到位
            if (htmlParmBo.getBigDivClass() != null) {
                //用class解析
                Elements titles = document.getElementsByAttributeValue("class", htmlParmBo.getBigDivClass());
                for (int i = 0; i < titles.size(); i++) {
                    //解析具体的影票属性
                    this.titleDetail(htmlParmBo, titles, i);
                }
            } else if (htmlParmBo.getBigDivTag() != null) {
                //用tag解析
                Elements titles = document.getElementsByTag(htmlParmBo.getBigDivTag());
                for (int i = 0; i < titles.size(); i++) {
                    //解析具体的影票属性
                    this.titleDetail(htmlParmBo, titles, i);
                }
            } else {
                //直接能一步解析到位
//                this.titleDetail(document,htmlParmBo);
                this.titleDetail(document,htmlParmBo);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    /**
     * @Description: 具体的影票属性解析(一步解析得到)
     * @param htmlParmBo
     * @return void
     * @date: 2020/10/8 17:26
     */
    private void titleDetail(Document document,HTMLParmBo htmlParmBo) {
        Elements titles = new Elements();
        Elements num = new Elements();
        Elements price = new Elements();
        Elements desc = new Elements();
        //按照class解析
        if (htmlParmBo.getTitleClass() != null){
            titles = document.getElementsByAttributeValue("class", htmlParmBo.getTitleClass());
        }
        if (htmlParmBo.getNumClass() != null){
            num = document.getElementsByAttributeValue("class", htmlParmBo.getNumClass());
        }
        if (htmlParmBo.getPriceClass() != null){
            price = document.getElementsByAttributeValue("class", htmlParmBo.getPriceClass());
        }
        if (htmlParmBo.getDescClass() != null){
            desc = document.getElementsByAttributeValue("class", htmlParmBo.getDescClass());
        }
        //按照tag解析
        if (htmlParmBo.getTitleTag() != null){
            titles = document.getElementsByTag(htmlParmBo.getTitleTag());
        }
        if (htmlParmBo.getNumTag() != null){
            num = document.getElementsByTag(htmlParmBo.getNumTag());
        }
        if (htmlParmBo.getPriceTag() != null){
            price = document.getElementsByTag(htmlParmBo.getPriceTag());
        }
        if (htmlParmBo.getDescTag() != null){
            desc = document.getElementsByTag(htmlParmBo.getDescTag());
        }
        if (titles.size() == price.size()){
            for (int i = 0; i < titles.size(); i++) {
                System.out.println(titles.get(i).text());
                Matcher matcher = this.getMatcher(price.get(i));
                if (matcher.find()){
                    System.out.println(matcher.group(1));
                }
            }
        }
        if (titles.size() == price.size() && titles.size() == num.size()){
            for (int i = 0; i < titles.size(); i++) {
                System.out.println(titles.get(i).text());
                Matcher priceMatcher = this.getMatcher(price.get(i));
                if (priceMatcher.find()){
                    System.out.println(priceMatcher.group(1));
                }
                Matcher numMatcher = this.getMatcher(num.get(i));
                if (numMatcher.find()){
                    System.out.println(numMatcher.group(1));
                }
            }
        }
        if (titles.size() == price.size() && titles.size() == num.size() && titles.size() == desc.size()){
            for (int i = 0; i < titles.size(); i++) {
                System.out.println(titles.get(i).text());
                Matcher priceMatcher = this.getMatcher(price.get(i));
                if (priceMatcher.find()){
                    System.out.println(priceMatcher.group(1));
                }
                Matcher numMatcher = this.getMatcher(num.get(i));
                if (numMatcher.find()){
                    System.out.println(numMatcher.group(1));
                }
                System.out.println(desc.get(i).text());
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
     * @Description: 具体的影票属性解析(需要两步解析得到)
     * @param htmlParmBo
     * @param titles
     * @param i
     * @return void
     * @date: 2020/10/8 17:17
     */
    private void titleDetail(HTMLParmBo htmlParmBo, Elements elements, int i) {
        Elements titles = new Elements();
        Elements num = new Elements();
        Elements price = new Elements();
        Elements desc = new Elements();
        //按照class解析
        if (htmlParmBo.getTitleClass() != null){
            titles = elements.get(i).getElementsByAttributeValue("class", htmlParmBo.getTitleClass());
        }
        if (htmlParmBo.getNumClass() != null){
            num = elements.get(i).getElementsByAttributeValue("class", htmlParmBo.getNumClass());
        }
        if (htmlParmBo.getPriceClass() != null){
            price = elements.get(i).getElementsByAttributeValue("class", htmlParmBo.getPriceClass());
        }
        if (htmlParmBo.getDescClass() != null){
            desc = elements.get(i).getElementsByAttributeValue("class", htmlParmBo.getDescClass());
        }
        //按照tag解析
        if (htmlParmBo.getTitleTag() != null){
            titles = elements.get(i).getElementsByTag(htmlParmBo.getTitleTag());
        }
        if (htmlParmBo.getNumTag() != null){
            num = elements.get(i).getElementsByTag(htmlParmBo.getNumTag());
        }
        if (htmlParmBo.getPriceTag() != null){
            price = elements.get(i).getElementsByTag(htmlParmBo.getPriceTag());
        }
        if (htmlParmBo.getDescTag() != null){
            desc = elements.get(i).getElementsByTag(htmlParmBo.getDescTag());
        }

        //影票所有的属性都用一个tag标签
        if (htmlParmBo.getTitleTag() != null && htmlParmBo.getPriceTag() == null ){
            for (int i1 = 0; i1 < titles.size(); i1++) {
                if (i1 == 0) {
                    //第一个默认肯定是标题
                    System.out.println(titles.get(0).text());
                }
                Matcher matcher = this.getMatcher(titles.get(i1));
                if (matcher.find()) {
                    if (matcher.group(1).split("\\.").length > 1) {
                        //带有小数点，是价格
                        System.out.println(matcher.group(1));
                    } else {
                        //是数量，或是其他的
                        System.out.println(matcher.group(1));
                    }
                }
            }
        }
        //影票所有的属性都用一个class标记
        if (htmlParmBo.getTitleClass() != null && htmlParmBo.getPriceClass() == null ){
            for (int i1 = 0; i1 < titles.size(); i1++) {
                if (i1 == 0) {
                    //第一个默认肯定是标题
                    System.out.println(titles.get(0).text());
                }
                Matcher matcher = this.getMatcher(titles.get(i1));
                if (matcher.find()) {
                    if (matcher.group(1).split("\\.").length > 1) {
                        //带有小数点，是价格
                        System.out.println(matcher.group(1));
                    } else {
                        //是数量，或是其他的
                        System.out.println(matcher.group(1));
                    }
                }
            }
        }
        if (titles.size() == price.size()){
            for (int j = 0; j < titles.size(); j++) {
                System.out.println(titles.get(j).text());
                Matcher matcher = this.getMatcher(price.get(j));
                if (matcher.find()){
                    System.out.println(matcher.group(1));
                }
            }
        }
        if (titles.size() == price.size() && titles.size() == num.size()){
            for (int j = 0; j < titles.size(); j++) {
                System.out.println(titles.get(j).text());
                Matcher priceMatcher = this.getMatcher(price.get(j));
                if (priceMatcher.find()){
                    System.out.println(priceMatcher.group(1));
                }
                Matcher numMatcher = this.getMatcher(num.get(j));
                if (numMatcher.find()){
                    System.out.println(numMatcher.group(1));
                }
            }
        }
        if (titles.size() == price.size() && titles.size() == num.size() && titles.size() == desc.size()){
            for (int j = 0; j < titles.size(); j++) {
                System.out.println(titles.get(j).text());
                Matcher priceMatcher = this.getMatcher(price.get(j));
                if (priceMatcher.find()){
                    System.out.println(priceMatcher.group(1));
                }
                Matcher numMatcher = this.getMatcher(num.get(j));
                if (numMatcher.find()){
                    System.out.println(numMatcher.group(1));
                }
                System.out.println(desc.get(j).text());
            }
        }
    }
}