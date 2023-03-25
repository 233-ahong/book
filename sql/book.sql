CREATE TABLE user_inf(
 user_inf_id INT UNSIGNED AUTO_INCREMENT NOT NULL COMMENT '主键ID',
 user_name varchar(30) not null comment '用户账号',
 nick_name varchar(30) not null comment '用户昵称',
 password varchar(100) NOT NULL COMMENT '密码',
 user_stats TINYINT NOT NULL DEFAULT 1 COMMENT '用户状态',
 user_avatar varchar(100) default '' comment '头像地址',
 mobile_phone varchar(11) default '' COMMENT '手机号',
 user_email VARCHAR(50) COMMENT '邮箱',
 gender CHAR(1) default '0' COMMENT '性别',
 register_time TIMESTAMP NOT NULL COMMENT '注册时间',
 modified_time TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '最后修改时间',
 user_role varchar(10) not null default 'user'comment '用户角色',
 PRIMARY KEY pk_userinfid(user_inf_id)
) ENGINE = innodb COMMENT '用户信息表';

CREATE TABLE book_category(
category_id SMALLINT UNSIGNED AUTO_INCREMENT NOT NULL COMMENT '分类ID',
category_name VARCHAR(10) NOT NULL COMMENT '分类名称',
category_code VARCHAR(10) NOT NULL COMMENT '分类编码',
parent_id SMALLINT UNSIGNED NOT NULL DEFAULT 0 COMMENT '父分类ID',
category_status TINYINT NOT NULL DEFAULT 1 COMMENT '分类状态',
modified_time TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT  '最后修改时间',
PRIMARY KEY pk_categoryid(category_id)
)ENGINE=innodb COMMENT '书籍分类表';

CREATE TABLE product_comment(
comment_id INT UNSIGNED AUTO_INCREMENT NOT NULL COMMENT '评论ID',
product_id INT UNSIGNED NOT NULL COMMENT '书本ID',
order_id BIGINT UNSIGNED NOT NULL COMMENT '订单ID',
customer_id INT UNSIGNED NOT NULL COMMENT '用户ID',
content VARCHAR(300) NOT NULL COMMENT '评论内容',
audit_status TINYINT NOT NULL default '0' COMMENT '审核状态：0未审核，1已审核',
audit_time TIMESTAMP NOT NULL COMMENT '评论时间',
modified_time TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '最后修改时间',
PRIMARY KEY pk_commentid(comment_id)
) ENGINE = innodb COMMENT '书籍评论表';

CREATE TABLE order_cart(
cart_id INT UNSIGNED NOT NULL AUTO_INCREMENT COMMENT '购物车ID',
user_id INT UNSIGNED NOT NULL COMMENT '用户ID',
book_id INT UNSIGNED NOT NULL COMMENT '书籍ID',
product_amount INT NOT NULL COMMENT '加入购物车数量',
price DECIMAL(8,2) NOT NULL COMMENT '书籍价格',
add_time TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '加入购物车时间',
modified_time TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '最后修改时间',
PRIMARY KEY pk_cartid(cart_id)
) ENGINE = innodb COMMENT '购物车表';

create table borrow(
    borrow_id      int unsigned auto_increment comment '借阅信息表ID'
        primary key,
    user_id        int unsigned                        not null comment '用户ID',
    user_name      varchar(50)                         not null comment '借阅人',
    book_id        int unsigned                        not null comment '图书ID',
    book_name      varchar(50)                         not null comment '图书名称',
    borrowing_time int                                 not null comment '借阅时长',
    remand_date    timestamp default CURRENT_TIMESTAMP not null on update CURRENT_TIMESTAMP comment '还书日期',
    modified_time  timestamp default CURRENT_TIMESTAMP not null comment '借阅时间',
    borrow_state   int       default 1                 not null comment '归还状态'
)ENGINE = innodb comment '借阅记录详情表';

create table order_customer_addr(
    customer_addr_id int unsigned auto_increment comment '自增主键ID'
        primary key,
    customer_id      int unsigned                        not null comment '用户ID',
    zip              smallint                            not null comment '邮编',
    province         smallint                            not null comment '省份',
    city             smallint                            not null comment '城市',
    district         smallint                            not null comment '区',
    address          varchar(200)                        not null comment '具体地址',
    is_default       tinyint                             not null comment '是否默认',
    modified_time    timestamp default CURRENT_TIMESTAMP not null on update CURRENT_TIMESTAMP comment '最后修改时间'
)ENGINE = innodb comment '用户地址表';

create table order_master(
    order_id           int unsigned auto_increment comment '订单ID'
        primary key,
    order_sn           bigint unsigned                         not null comment '订单编号 yyyymmddnnnnnnnn',
    customer_id        int unsigned                            not null comment '下单人ID',
    shipping_user      varchar(10)                             not null comment '收货人姓名',
    province           int                                     not null comment '省',
    city               int                                     not null comment '市',
    district           int                                     not null comment '区',
    address            varchar(100)                            not null comment '地址',
    order_money        decimal(8, 2)                           not null comment '订单金额',
    district_money     decimal(8, 2) default 0.00              not null comment '优惠金额',
    shipping_money     decimal(8, 2) default 0.00              not null comment '运费金额',
    payment_money      decimal(8, 2) default 0.00              not null comment '支付金额',
    shipping_comp_name varchar(10)                             null comment '快递公司名称',
    shipping_sn        varchar(50)                             null comment '快递单号',
    create_time        timestamp     default CURRENT_TIMESTAMP not null comment '下单时间',
    shipping_time      datetime                                null comment '发货时间',
    pay_time           datetime                                null comment '支付时间',
    receive_time       datetime                                null comment '收货时间',
    order_status       tinyint       default 0                 not null comment '订单状态',
    invoice_time       varchar(100)                            null comment '发票抬头',
    modified_time      timestamp     default CURRENT_TIMESTAMP not null on update CURRENT_TIMESTAMP comment '最后修改时间'
)ENGINE = innodb comment '订单主表';

create table order_detail(
    order_detail_id int unsigned auto_increment comment '订单详情表ID'
        primary key,
    order_id        int unsigned                            not null comment '订单表ID',
    product_id      int unsigned                            not null comment '书籍ID',
    product_name    varchar(50)                             not null comment '书名',
    product_cnt     int           default 1                 not null comment '购买数量',
    product_price   decimal(8, 2)                           not null comment '购买单价',
    weight          float                                   null comment '商品重量',
    fee_money       decimal(8, 2) default 0.00              not null comment '优惠分摊金额',
    modified_time   timestamp     default CURRENT_TIMESTAMP not null on update CURRENT_TIMESTAMP comment '最后修改时间'
)ENGINE = innodb comment '订单详情表';

create table book_info(
    book_id        int unsigned auto_increment comment '图书ID'
        primary key,
    book_core      char(16)                            not null comment '图书编码',
    book_name      varchar(20)                         not null comment '图书名称',
    bar_code       varchar(50)                         not null comment '国条码',
    book_press     varchar(32)                         not null comment '图书出版社',
    book_author    varchar(32)                         not null comment '图书作者',
    cover_address  text                                not null comment '封面地址',
    book_category  smallint unsigned                   not null comment '分类',
    price          decimal(8, 2)                       not null comment '图书销售价格',
    publish_status tinyint   default 0                 not null comment '上下架状态：0下架1上架',
    audit_status   tinyint   default 0                 not null comment '审核状态：0未审核，1已审核',
    book_page      mediumtext                          not null comment '图书页数',
    descript       text                                not null comment '图书描述',
    indate         timestamp default CURRENT_TIMESTAMP not null comment '图书录入时间',
    modified_time  timestamp default CURRENT_TIMESTAMP not null on update CURRENT_TIMESTAMP comment '最后修改时间',
    book_remaining int       default 1                 null comment '图书余量'
)ENGINE = innodb comment '图书信息表';

