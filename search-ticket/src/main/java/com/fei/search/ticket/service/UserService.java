package com.fei.search.ticket.service;

import com.fei.search.ticket.bo.UserBo;
import com.fei.search.ticket.util.ApiMessage;
import org.springframework.web.bind.annotation.RequestBody;

public interface UserService {
    public String addUser(UserBo user);

    public UserBo doLogin(UserBo user);

    public String updatePassword(UserBo user);
}
