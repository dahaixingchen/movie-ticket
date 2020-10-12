package com.fei.movieticket.elasticSearch;

import org.springframework.data.elasticsearch.annotations.Document;

import com.fasterxml.jackson.annotation.JsonProperty;

//indexName代表所以名称,type代表表名称
@Document(indexName = "wantu_notice_info", type = "doc")
public class Notice {

    //id
    @JsonProperty("auto_id")
    private Long id;

    //标题
    @JsonProperty("title")
    private String title;

    //公告标签
    @JsonProperty("exchange_mc")
    private String exchangeMc;

    //公告发布时间
    @JsonProperty("create_time")
    private String originCreateTime;

    //公告阅读数量
    @JsonProperty("read_count")
    private Integer readCount;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getExchangeMc() {
        return exchangeMc;
    }

    public void setExchangeMc(String exchangeMc) {
        this.exchangeMc = exchangeMc;
    }

    public String getOriginCreateTime() {
        return originCreateTime;
    }

    public void setOriginCreateTime(String originCreateTime) {
        this.originCreateTime = originCreateTime;
    }

    public Integer getReadCount() {
        return readCount;
    }

    public void setReadCount(Integer readCount) {
        this.readCount = readCount;
    }

    public Notice(Long id, String title, String exchangeMc, String originCreateTime, Integer readCount) {
        super();
        this.id = id;
        this.title = title;
        this.exchangeMc = exchangeMc;
        this.originCreateTime = originCreateTime;
        this.readCount = readCount;
    }

    public Notice() {
        super();
    }


}