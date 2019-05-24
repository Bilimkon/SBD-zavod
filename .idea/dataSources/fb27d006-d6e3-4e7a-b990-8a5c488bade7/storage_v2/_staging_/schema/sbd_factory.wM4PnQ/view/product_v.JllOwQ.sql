create or replace view product_v as
select `p`.`id`                                                      AS `id`,
       (case `p`.`unit` when '1' then 'Dona' when '2' then 'Kg' when  '3' then 'Rulon' when '4' then 'Litr' end) AS `unit`,
       `p`.`barcode`                                                 AS `barcode`,
       `p`.`name`                                                    AS `name`,
       `p`.`type`                                                    AS `type`,
       `p`.`cost`                                                    AS `cost`,
       `p`.`quantity`                                                AS `quantity`,
       (`p`.`quantity` * `p`.`cost`)                                 AS `total_cost`,
       `p`.`weight`                                                  AS `weight`,
       `s`.`companyName`                                               AS `suplier`,
       `p`.`date_cr`                                                 AS `date_cr`,
       `u`.`username`                                               AS `user`,
        p.description                                                AS description,
        p.width                 as width,
        p.height                as height,
        p.color                 as color
from (`sbd_factory`.`product` `p`
       join `sbd_factory`.`suplier` `s`) join user u where p.cr_by = u.id and
(`p`.`suplier_id` = `s`.`id`);

-- comment on view product_v not supported: View 'sbd_factory.product_v' references invalid table(s) or column(s) or function(s) or definer/invoker of view lack rights to use them

