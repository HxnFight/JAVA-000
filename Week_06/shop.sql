create table if not exists t_user
(
    id          bigint auto_increment comment '表id',
    user_id     bigint        not null comment '用户id',
    user_type   int           null comment '用户类型',
    nick_name   varchar(100)  not null comment '用户昵称',
    phone       varchar(20)   not null,
    gender      int default 3 null comment '1:男，2:女，3:未设置',
    status      int default 1 not null comment '用户状态【1:有效,2:无效】',
    create_time bigint        not null comment '创建时间（毫秒值）',
    update_time bigint        null comment '更新时间（毫秒值）',
    constraint t_user_pk primary key (id),
    unique index t_user_uid (user_id)
) engine = InnoDB
  auto_increment = 1
  default charset = utf8 comment '用户信息表';

create table if not exists t_order
(
    id             bigint auto_increment comment '表id',
    trans_id       varchar(100)  not null comment '订单id',
    order_type     int           not null comment '订单类型',
    amount         double(11, 2) not null comment '订单金额',
    sale_type      int default 1 not null comment '促销类型 【1:无促销，2:大促,3: and so on ]',
    goods_id       varchar(200)  not null comment '商品ids',
    purchase_phone varchar(20)   not null comment '购买人联系方式',
    purchase_addr  varchar(200)  not null comment '购买人地址',
    status         int default 1 not null comment '订单状态【1:有效,2:无效,3: 发货】',
    create_time    bigint        not null comment '创建时间（毫秒值）',
    update_time    bigint        null comment '更新时间（毫秒值）',
    constraint trans_pk primary key (id),
    unique index trans_id (trans_id)
) engine = InnoDB
  auto_increment = 1
  default charset = utf8 comment '订单表';


create table if not exists t_goods
(
    id           bigint auto_increment comment '表id',
    goods_id     varchar(100)     not null comment '商品id',
    goods_desc   varchar(100)     not null comment '商品描述',
    goods_source varchar(100)     not null comment '商品来源',
    goods_stock  bigint default 0 not null comment '商品库存量',
    goods_type   int              not null comment '商品类型',
    price        double(11, 2)    not null comment '商品单价金额',
    status       int    default 1 not null comment '订单状态【1:有效,2:下架】',
    create_time  bigint           not null comment '创建时间（毫秒值）',
    update_time  bigint           null comment '更新时间（毫秒值）',
    constraint goods_pk primary key (id),
    unique index goods_id (goods_id)
) engine = InnoDB
  auto_increment = 1
  default charset = utf8 comment '商品表';
