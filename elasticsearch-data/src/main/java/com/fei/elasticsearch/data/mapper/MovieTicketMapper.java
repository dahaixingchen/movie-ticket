package com.fei.elasticsearch.data.mapper;


import com.fei.elasticsearch.data.bo.URLBo;

import java.util.List;

public interface MovieTicketMapper {

    /**
     * @Description: 从数据库中得到URL的相关信息
      * @param
     * @return java.util.List<com.fei.movieticket.bo.URLBo>
     * @date: 2020/10/7 14:43
     */
    List<URLBo> getUrlBo();

    List<URLBo> getPortUrlBo();
}
