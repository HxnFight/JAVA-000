create
    definer = root@`%` procedure mock_data(IN cnt int)
begin

    declare var int default 1;
    set var = 1;
    #开启事物
    #     start transaction ;
    while var < cnt
        do
            insert into t_order (trans_id,
                                 order_type,
                                 amount,
                                 sale_type,
                                 goods_id,
                                 purchase_phone,
                                 purchase_addr,
                                 create_time,
                                 update_time)
            values (var,
                    var + 1,
                    var + 2,
                    var + 3,
                    var + 4,
                    var + 5,
                    var + 6,
                    unix_timestamp(),
                    unix_timestamp());

            set var = var + 1;

        end while;
    #     commit;
end;
