package com.fei.movieticket.controller;

import com.fei.movieticket.bo.QueryConditionBo;
import com.fei.movieticket.service.MovieTicketService;
import com.fei.movieticket.util.ApiMessage;
import com.fei.movieticket.util.MessageConstant;
import com.fei.movieticket.vo.TicketVo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @ClassName: MovieTicketController
 * @Author chengfei
 * @Date 2020/9/27 19:00
 * @Description: TODO
 **/
@Api("电影票")
@RestController
@RequestMapping("/movie")
@CrossOrigin
public class MovieTicketController {

    @Autowired
    private MovieTicketService movieTicketServiceImpl;

    @ApiOperation(value = "电影票通兑券")
    @PostMapping("/ticket")
    public ApiMessage<TicketVo> ticket(@RequestBody QueryConditionBo queryConditionBo) {
        if ("".equals(queryConditionBo.getCinema())){
            queryConditionBo.setCinema(null);
        }
        if ("".equals(queryConditionBo.getPrice())){
            queryConditionBo.setPrice(null);
        }
        if ("".equals(queryConditionBo.getPlatform())){
            queryConditionBo.setPlatform(null);
        }
        if ("".equals(queryConditionBo.getArea())){
            queryConditionBo.setArea(null);
        }
        if ("".equals(queryConditionBo.getMovieName())){
            queryConditionBo.setMovieName(null);
        }
        if (queryConditionBo.getPlatform() == null && queryConditionBo.getPrice() == null && queryConditionBo.getMovieName() == null
                && queryConditionBo.getArea() == null && queryConditionBo.getCinema() == null){
            return ApiMessage.error(MessageConstant.ALL_NULL);
        }
        List<TicketVo> tickets = movieTicketServiceImpl.disposeTicketResult(queryConditionBo);
        ApiMessage success = ApiMessage.success(MessageConstant.QUERY_SUCCESS_MESSAGE, tickets.size(), tickets);
        return success;
    }
}
