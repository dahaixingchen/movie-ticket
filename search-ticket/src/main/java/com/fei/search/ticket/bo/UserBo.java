package com.fei.search.ticket.bo;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @ClassName: UserBo
 * @Date 2020/6/17 20:51
 * @Version 1.0
 * @Description: TODO
 **/
@Data
public class UserBo {

    @ApiModelProperty(value = "名称")
    private String id;

    @ApiModelProperty(value = "名称")
    private String name;

    @ApiModelProperty(value = "手机号",required = true)
    private String phone;

    @ApiModelProperty(value = "密码",required = true)
    private String password;

    @ApiModelProperty(value = "确认密码，在创建账号和忘记密码，修改密码的时候用",required = true)
    private String confirmPassword;

    @ApiModelProperty(value = "是否为管理员，0:普通用户 1:管理员",required = true)
    private String userType;
}
