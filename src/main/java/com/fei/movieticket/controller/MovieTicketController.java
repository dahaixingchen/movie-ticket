package com.fei.movieticket.controller;

import com.fei.movieticket.util.ApiMessage;
import com.fei.movieticket.util.MessageConstant;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @ClassName: MovieTicketController
 * @Author chengfei
 * @Date 2020/9/27 19:00
 * @Description: TODO
 **/
@Api("电影票")
@RestController
@RequestMapping("/movie")
public class MovieTicketController {
    @ApiOperation(value = "电影票通兑券")
    @GetMapping("/ticket")
    public ApiMessage addQuestions() {

        return ApiMessage.success(MessageConstant.ADD_SUCCESS_MESSAGE);
    }
}
