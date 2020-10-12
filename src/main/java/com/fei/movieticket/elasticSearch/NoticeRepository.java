package com.fei.movieticket.elasticSearch;

import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;
import org.springframework.stereotype.Component;

@Component
public interface NoticeRepository extends ElasticsearchRepository<Notice, Long> {

}