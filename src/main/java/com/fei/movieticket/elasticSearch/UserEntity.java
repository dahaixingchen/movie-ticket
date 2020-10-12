package com.fei.movieticket.elasticSearch;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.Document;

/**
 * Create by wangxb
 * 2019-09-08 18:48
 */
@Document(indexName = "basic",indexStoreType = "_refresh")
@Data
public class UserEntity {

    @Id
    private String id;
    private String _class;
    private String name;
    private Integer age;
    private String address;
}