package com.fei.search.ticket.controller;

import com.fei.search.ticket.bo.QueryConditionBo;
import com.fei.search.ticket.service.MovieTicketService;
import com.fei.search.ticket.util.ApiMessage;
import com.fei.search.ticket.util.MessageConstant;
import com.fei.search.ticket.vo.TicketVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

/**
 * @ClassName: MovieTicketController
 * @Author chengfei
 * @Date 2020/9/27 19:00
 * @Description: TODO
 **/
@RestController
@RequestMapping("/movie")
@CrossOrigin
public class MovieTicketController {

    @Autowired
    private MovieTicketService movieTicketServiceImpl;

    @PostMapping("/search")
    public ApiMessage search(HttpServletRequest request, HttpServletResponse response
            ,@RequestBody QueryConditionBo queryConditionBo){

        return null;
    }

    @PostMapping("/ticket")
    public ApiMessage<TicketVo> ticket(@RequestBody QueryConditionBo queryConditionBo) {
//        Boolean vip = movieTicketServiceImpl.checkVip(queryConditionBo.getUserId());
//        if (!vip){
//            return ApiMessage.error("对不起，您不是VIP，请联系服务员小姐姐呦( 微信：muyeafei_88 或扫下方二维码)");
//        }
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
