package com.fei.search.ticket.mapper;


import com.fei.search.ticket.bo.UserBo;

public interface UserMapper {

    Integer addUser(UserBo user);

    UserBo doLogin(UserBo userBo);

    String queryPhone(UserBo userBo);

    Integer updatePassword(UserBo user);
}
