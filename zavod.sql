create table product
(
  id          int auto_increment,
  barcode     varchar(30)    null,
  name        varchar(100)   null,
  type        varchar(100)   null,
  type_id     int            null,
  cost        decimal(15, 2) null,
  quantity    decimal(15, 2) null,
  weight      decimal(15, 2) null,
  cr_by       int            null,
  date_cr     varchar(40)    null,
  unit        int            null,
  description varchar(500)   null,
  suplier_id  int            null,
  color       varchar(100)   null,
  height      int            null,
  width       int            null,
  constraint id
    unique (id)
);

alter table product
  add primary key (id);

create table suplier
(
  id          int auto_increment
    primary key,
  companyName varchar(100) null,
  person      varchar(100) null,
  info        varchar(300) null,
  date_cr     varchar(40)  null,
  cr_by       int          null,
  constraint suplier_companyName_uindex
    unique (companyName)
);

create table type
(
  id      int auto_increment
    primary key,
  name    varchar(100)         null,
  unit    tinyint(1) default 1 null,
  info    varchar(300)         null,
  date_cr varchar(40)          null,
  cr_by   int                  null,
  constraint type_name_uindex
    unique (name)
);

create table user
(
  id        int auto_increment
    primary key,
  username  varchar(20) null,
  firstname varchar(20) null,
  lastname  varchar(20) null,
  password  varchar(20) null,
  userType  int(3)      null,
  phone     varchar(15) null,
  constraint user_username_uindex
    unique (username)
);

create table utils
(
  printerName varchar(300) null,
  filePath    varchar(300) null,
  dollar      varchar(10)  null
);

create definer = java@`%` view product_v as
select `p`.`id`                      AS `id`,
       (case `p`.`unit`
          when '1' then 'Dona'
          when '2' then 'Kg'
          when '3' then 'Rulon'
          when '4'
            then 'Litr' end)         AS `unit`,
       `p`.`barcode`                 AS `barcode`,
       `p`.`name`                    AS `name`,
       `p`.`type`                    AS `type`,
       `p`.`cost`                    AS `cost`,
       `p`.`quantity`                AS `quantity`,
       (`p`.`quantity` * `p`.`cost`) AS `total_cost`,
       `p`.`weight`                  AS `weight`,
       `s`.`companyName`             AS `suplier`,
       `p`.`date_cr`                 AS `date_cr`,
       `u`.`username`                AS `user`,
       `p`.`description`             AS `description`,
       `p`.`width`                   AS `width`,
       `p`.`height`                  AS `height`,
       `p`.`color`                   AS `color`
from ((`sbd_factory`.`product` `p` join `sbd_factory`.`suplier` `s`)
       join `sbd_factory`.`user` `u`)
where ((`p`.`cr_by` = `u`.`id`) and (`p`.`suplier_id` = `s`.`id`));

create definer = java@`%` view type_v as
select `t`.`id`              AS `id`,
       `t`.`name`            AS `name`,
       `t`.`info`            AS `info`,
       `t`.`date_cr`         AS `date_cr`,
       `u`.`username`        AS `cr_by`,
       (case `t`.`unit`
          when '1' then 'Dona'
          when '2' then 'Kg'
          when '3' then 'Rulon'
          when '4'
            then 'Litr' end) AS `unit`
from (`sbd_factory`.`type` `t`
       join `sbd_factory`.`user` `u`)
where (`t`.`cr_by` = `u`.`id`);

create definer = java@`%` view user_v as
select `u`.`id`                      AS `id`,
       `u`.`username`                AS `username`,
       `u`.`firstname`               AS `firstname`,
       `u`.`lastname`                AS `lastname`,
       `u`.`password`                AS `password`,
       `u`.`phone`                   AS `phone`,
       (case `u`.`userType`
          when '1' then 'Ombor'
          when '2' then '1-ish/ch'
          when '3' then '2-ish/ch'
          when '4' then 'Savdo'
          when '5' then 'Admin' end) AS `userType`
from `sbd_factory`.`user` `u`
order by `u`.`id`;

