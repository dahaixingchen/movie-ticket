create table movie_ticket_html(
	id bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键 默认自动递增',
	url varchar(256) not null comment'网页地址',
	buy_rule varchar(256) DEFAULT NULL comment '购买网页的规则地址',
	big_div_class varchar(32) comment'包裹影票内容的class名称,在没有办法一下子定位到标题，价格，数量时用',
	big_div_class2 varchar(32) DEFAULT NULL COMMENT '一般前端是用for循环展现数据的，这个如果for之外的解析class',
    big_other_label varchar(32) DEFAULT NULL COMMENT '除了class和tag，其他的方式解析的',
    big_other_label_type varchar(256) DEFAULT NULL COMMENT '其他的方式解析对应的value值',
	title_class varchar(32) comment'标题的html，class标签',
	num_class varchar(32) comment'影票数量的html，class标签',
	price_class varchar(32) comment'影票价格的html，class标签',
	desc_class varchar(32) comment'影票描述的html，class标签',
	url_class varchar(32) comment'影票购买的html，class标签',
	big_div_tag varchar(32) comment'包裹影票内容的tag大标签名称，在没有class属性的情况下使用',
	standby_tag1 varchar(32) comment'备用字段，用于解析的时候需要多个tag',
	standby_tag2 varchar(32) comment'备用字段，用于解析的时候需要多个tag',
	title_tag varchar(32) comment'标题的html，tag标签',
	num_tag varchar(32) comment'影票数量的html，tag标签',
	price_tag varchar(32) comment'影票价格的html，tag标签',
	desc_tag varchar(32) comment'影票描述的html，tag标签',
	url_tag varchar(32) comment'影票购买的html，tag标签',
	buy_rule varchar(256) comment'购买网页的规则，null表示没有购买的规则',
	create_time timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
	update_time timestamp not NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '修改时间',
	unique(url),
	PRIMARY KEY (id)
) comment'get请求的方式直接解析HTML，所需信息表';

CREATE TABLE movie_ticket_port (
  id bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键 默认自动递增',
  url varchar(256) NOT NULL COMMENT '网页地址',
  url_web varchar(256) NOT NULL COMMENT '解析客户网站的url',
  buy_rule varchar(256) DEFAULT NULL comment '购买网页的规则地址',
  referer varchar(512) DEFAULT NULL COMMENT '网址请求时候的头，表示是从哪个网址上过来的',
  user_agent text DEFAULT NULL COMMENT '发起请求端的识别',
  cookie text DEFAULT NULL COMMENT '地址的cookie值',
  buy_rule varchar(256) comment'购买网页的规则，null表示没有购买的规则',
  mark varchar(20) COMMENT '网站标识，为null的是91网站的识别方式',
  create_time timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  update_time timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '修改时间',
  unique(url_web),
  PRIMARY KEY (id)
) ENGINE=InnoDB AUTO_INCREMENT=9 DEFAULT CHARSET=utf8mb4 COMMENT='post请求的方式直接解析HTML，所需信息表';

create table pay_money(
  id bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键 默认自动递增',
  user_id varchar(32) NOT NULL COMMENT '标识用户的id，一般为手机号',
  type varchar(32) NOT NULL COMMENT '支付的类型，如 weixin,zhifubao',
  money double NOT NULL COMMENT '金额，以分为单位',
  PRIMARY KEY (id)
)comment '付费表';

create table vip(
  id bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键 默认自动递增',
  user_id varchar(32) NOT NULL COMMENT '标识用户的id，一般为手机号',
  type varchar(32) NOT NULL COMMENT 'vip的类型',
  past_time timestamp NOT NULL COMMENT 'vip的过期时间',
  click_num int not null comment '可用于点击查询的次数',
  PRIMARY KEY (id)
)comment '付费表';