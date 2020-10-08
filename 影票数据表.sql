create table movie_ticket_html(
	id bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键 默认自动递增',
	url varchar(1024) not null comment'网页地址',
	big_div_class varchar(32) comment'包裹影票内容的class名称,在没有办法一下子定位到标题，价格，数量时用',
	standby_class1 varchar(32) comment'备用字段，用于解析的时候需要多个class',
	standby_class2 varchar(32) comment'备用字段，用于解析的时候需要多个class',
	title_class varchar(32) comment'标题的html，class标签',
	num_class varchar(32) comment'影票数量的html，class标签',
	price_class varchar(32) comment'影票价格的html，class标签',
	desc_class varchar(32) comment'影票描述的html，class标签',
	big_div_tag varchar(32) comment'包裹影票内容的tag大标签名称，在没有class属性的情况下使用',
	standby_tag1 varchar(32) comment'备用字段，用于解析的时候需要多个tag',
	standby_tag2 varchar(32) comment'备用字段，用于解析的时候需要多个tag',
	title_tag varchar(32) comment'标题的html，tag标签',
	num_tag varchar(32) comment'影票数量的html，tag标签',
	price_tag varchar(32) comment'影票价格的html，tag标签',
	desc_tag varchar(32) comment'影票描述的html，tag标签',
	PRIMARY KEY (`id`)
) comment'get请求的方式直接解析HTML，所需信息表';