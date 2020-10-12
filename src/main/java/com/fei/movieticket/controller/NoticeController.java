package com.fei.movieticket.controller;

import java.util.ArrayList;
import java.util.List;

import com.fei.movieticket.elasticSearch.Notice;
import com.fei.movieticket.elasticSearch.NoticeRepository;
import org.elasticsearch.index.query.QueryBuilder;
import org.elasticsearch.index.query.QueryBuilders;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.google.common.collect.Lists;

@RestController
@RequestMapping("/api/v1/article")
public class NoticeController {


    @Autowired
    private NoticeRepository nticeRepository;

    @GetMapping("/save")
    public Object save(long id, String title){

        Notice article = new Notice();
        article.setId(id);
        article.setReadCount(123);
        article.setTitle(title);
        return nticeRepository.save(article);
    }


    /**
     * @param title   搜索标题
     * @param pageable page = 第几页参数, value = 每页显示条数
     */
    @GetMapping("/search")
    public Object search(String title ,@PageableDefault() Pageable pag){

        //按标题进行搜索
        QueryBuilder builder = QueryBuilders.matchQuery("title", title);
        //如果实体和数据的名称对应就会自动封装，pageable分页参数
        Iterable<Notice> listIt =  nticeRepository.search(builder, pag);
        //Iterable转list
        List<Notice> list= Lists.newArrayList(listIt);

        return list;
    }


    @RequestMapping("/all")
    public List<Notice> all() throws Exception {
        Iterable<Notice> data = nticeRepository.findAll();
        List<Notice> ds = new ArrayList<>();
        for (Notice d : data) {
            ds.add(d);
        }
        return ds;
    }
    @RequestMapping("/id")
    public Object findid(long id) throws Exception {
        return nticeRepository.findById(id).get();


    }

}