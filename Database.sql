-- we don't know how to generate schema sbd_factory (class Schema) :(
create table accountexpense
(
	id int auto_increment
		primary key,
	schot varchar(100) null,
	schotDate varchar(100) null,
	firmaName varchar(200) null,
	shartnoma varchar(300) null,
	hr decimal(20,2) null,
	dollar decimal(20,2) null,
	izoh varchar(500) null,
	shartnomaDate varchar(100) null
)
;

create trigger insert_omborReport1
after INSERT on accountexpense
for each row
insert into reportOmbor ( type, invoiceName, company, account, contract, dollar, sum, hr, maxsulot, izoh, cr_on, cr_by)
      values ('Chiqim', ':', (select companyName from person where id=NEW.firmaName), new.schot, new.shartnoma, NEW.dollar, 0, NEW.hr, ':', NEW.izoh, new.schotDate, 1 )
;

create trigger updateBalance_accountExpense
after INSERT on accountexpense
for each row
update balance set dollar_in = dollar_in+ new.dollar, hr_in = hr_in + new.hr where who = new.firmaName
;

create trigger updateTotalBalance_accountExpense12
after INSERT on accountexpense
for each row
update total_balance set hr = hr- NEW.hr, vhr= vhr - new.dollar where id=1
;

create table admin_log_table
(
	id int auto_increment
		primary key,
	module varchar(100) null,
	type varchar(100) null,
	ksum decimal(20,2) null,
	kdollar decimal(20,2) null,
	khr decimal(20,2) null,
	csum decimal(20,2) null,
	cdollar decimal(20,2) null,
	chr decimal(20,2) null,
	cr_by int null,
	date varchar(40) null,
	comment varchar(400) null
)
;

create trigger addMoneyToBalanceTotal
after INSERT on admin_log_table
for each row
update total_balance set sum=sum+ NEW.ksum, hr = hr+ NEW.khr, dollar =dollar+NEW.kdollar where id=1
;

create trigger setEmptyBalance
after INSERT on admin_log_table
for each row
update balance set sum_in=0, sum_out=0, dollar_in=0, dollar_out=0, hr_in=0, hr_out=0 where who=99999
;

create table admin_oper_r
(
	id int auto_increment
		primary key,
	type varchar(100) null,
	who int null,
	sum decimal(20,2) null,
	dollar decimal(20,2) null,
	hr decimal(20,2) null
)
;

create trigger admin_oper_update_total_balance_r
after INSERT on admin_oper_r
for each row
if new.type='Kirim' then
  update total_balance set sum=sum - NEW.sum, dollar=dollar - NEW.dollar, hr=hr - NEW.hr where id=1;
  else
    update total_balance set sum=sum + NEW.sum, dollar=dollar + NEW.dollar, hr=hr + NEW.hr where id=1;
  end if
;

create trigger admin_oper_update_who_balance_r
after INSERT on admin_oper_r
for each row
if new.type='Kirim' then
  update balance set sum_in=sum_in + NEW.sum, dollar_in=dollar_in + NEW.dollar, hr_in=hr_in + NEW.hr where who=NEW.who;
  else
    update balance set sum_out=sum_out + NEW.sum, dollar_out=dollar_out + NEW.dollar, hr_out=hr_out + NEW.hr where who=NEW.who;
  end if
;

create table adminoper
(
	id int auto_increment
		primary key,
	type varchar(100) null,
	sum decimal(20,2) null,
	dollar decimal(20,2) null,
	hr decimal(20,2) null,
	vhr decimal(20,2) null,
	comment varchar(500) null,
	cr_on varchar(40) null,
	cr_by int null,
	who int null
)
;

create trigger admin_oper_update_total_balance
after INSERT on adminoper
for each row
if new.type='Chiqim' then
  update total_balance set sum=sum - NEW.sum, dollar=dollar - NEW.dollar, hr=hr - NEW.hr where id=1;
  else
    update total_balance set sum=sum + NEW.sum, dollar=dollar + NEW.dollar, hr=hr + NEW.hr where id=1;
  end if
;

create trigger admin_oper_update_who_balance
after INSERT on adminoper
for each row
if new.type='Chiqim' then
  update balance set sum_in=sum_in + NEW.sum, dollar_in=dollar_in + NEW.dollar, hr_in=hr_in + NEW.hr where who=NEW.who;
  else
    update balance set sum_out=sum_out + NEW.sum, dollar_out=dollar_out + NEW.dollar, hr_out=hr_out + NEW.hr where who=NEW.who;
  end if
;

create table balance
(
	id int auto_increment
		primary key,
	who int null,
	sum_in decimal(20,2) default '0.00' null,
	sum_out decimal(20,2) default '0.00' null,
	dollar_in decimal(20,2) default '0.00' null,
	dollar_out decimal(20,2) default '0.00' null,
	hr_in decimal(20,2) default '0.00' null,
	hr_out decimal(20,2) default '0.00' null
)
;

create table color
(
	id int auto_increment
		primary key,
	name varchar(200) null,
	description varchar(500) null,
	constraint color_name_uindex
		unique (name)
)
;

create table dollarexchange
(
	id int auto_increment
		primary key,
	hr decimal(20,2) null,
	dollar decimal(20,2) null,
	rate decimal(20,2) null,
	who int null,
	description varchar(500) null,
	sana varchar(100) null
)
;

create table exchange_log
(
	id int auto_increment
		primary key,
	Name varchar(200) null,
	barcode varchar(45) null,
	type int null,
	quantity int null,
	comment varchar(200) null,
	cr_on varchar(45) null,
	cr_by varchar(20) null
)
;

create table history
(
	id int auto_increment
		primary key,
	barcode varchar(30) null,
	p_id int null,
	name varchar(300) null,
	type varchar(200) null,
	quantity int null,
	seller_id int null,
	cost decimal(15,2) null,
	date_cr varchar(40) null,
	cr_by int null,
	customer_id int null,
	sellAction_id int null
)
;

create trigger insertReport1_history
after INSERT on history
for each row
update report1
  set product = concat(product,'',concat(NEW.name,'*',new.quantity,'=',new.cost, ', '))
  where  report1.s_id= new.sellAction_id
;

create trigger update_sell
after INSERT on history
for each row
update sell
    set quantity = quantity - new.quantity
    where barcode = new.barcode
;

create table history_r
(
	id int auto_increment
		primary key,
	barcode varchar(30) null,
	name varchar(300) null,
	quantity int null,
	sellAction_id int null
)
;

create trigger revertPerProduct
after INSERT on history_r
for each row
update sell set quantity = quantity+new.quantity where barcode=new.barcode
;

create table invoice
(
	id int auto_increment
		primary key,
	name varchar(100) null,
	company varchar(100) null,
	currency varchar(100) null,
	total_price varchar(100) null,
	date varchar(100) null,
	user_id int not null,
	constraint name
		unique (name)
)
;

create trigger insert_omborReport
after INSERT on invoice
for each row
if NEW.currency='Sum' then
  insert into reportOmbor (i_id, type, invoiceName, company, account, contract, dollar, sum, hr, maxsulot, izoh, cr_on, cr_by)
      values(NEW.id, 'Ombor kirimi', NEW.name, NEW.company, ':', ':', 0, 0, NEW.total_price, ':', ':', NEW.date, NEW.user_id );
  else
  insert into reportOmbor (i_id, type, invoiceName, company, account, contract, dollar, sum, hr, maxsulot, izoh, cr_on, cr_by)
      values(NEW.id, 'Ombor kirimi', NEW.name, NEW.company, ':', ':', NEW.total_price, 0, 0, ':', ':', NEW.date, NEW.user_id );
  End if
;

create table log
(
	id int auto_increment
		primary key,
	module varchar(200) null,
	type varchar(200) null,
	cr_by int null,
	date varchar(200) null,
	comment varchar(500) null,
	cost varchar(100) default '0.00' null,
	summa varchar(100) default '0' null
)
;

create table manlist
(
	id int auto_increment
		primary key,
	name varchar(200) null,
	barcode varchar(20) null,
	type int null,
	description varchar(600) null
)
;

create table marketing_filter
(
	id int auto_increment
		primary key,
	company int null,
	name varchar(100) null,
	barcode_o varchar(100) null,
	barcode varchar(100) null,
	color varchar(100) null,
	cost decimal(15,2) null
)
;

create table p2list
(
	id int auto_increment
		primary key,
	name varchar(200) null,
	barcode varchar(40) null,
	type varchar(40) null,
	description varchar(300) null,
	constraint p2list_name_uindex
		unique (name),
	constraint p2list_barcode_uindex
		unique (barcode)
)
;

create table person
(
	id int auto_increment
		primary key,
	type int null,
	companyName varchar(200) null,
	account varchar(40) null,
	phone varchar(15) null,
	info varchar(300) null,
	cr_by int null,
	date_cr varchar(40) null,
	constraint companyName_UNIQUE
		unique (companyName)
)
;

create trigger addCustomer
after INSERT on person
for each row
insert into balance ( who, sum_in, sum_out, dollar_in, dollar_out, hr_in, hr_out) values(new.id,0,0,0,0,0,0)
;

create table product
(
	id int auto_increment
		primary key,
	barcode varchar(30) not null,
	name varchar(100) null,
	type varchar(100) null,
	type_id int null,
	cost decimal(15,2) null,
	quantity decimal(15,2) null,
	unit int null,
	suplier_id int null,
	invoice_id int not null,
	color varchar(100) null,
	description varchar(500) null,
	date_cr varchar(40) null,
	cr_by int null,
	constraint id
		unique (id),
	constraint barcode
		unique (barcode),
	constraint product_name_uindex
		unique (name)
)
;

create table product_h
(
	id int auto_increment
		primary key,
	oper_type varchar(200) null,
	barcode varchar(30) null,
	name varchar(100) null,
	type varchar(100) null,
	type_id int null,
	cost decimal(15,2) null,
	quantity decimal(15,2) null,
	unit int null,
	suplier_id int null,
	invoice_id int not null,
	color varchar(100) null,
	description varchar(500) null,
	date_cr varchar(40) null,
	cr_by int null,
	constraint id
		unique (id)
)
;

create trigger insertOmborReport2
after INSERT on product_h
for each row
update reportOmbor
  set maxsulot = concat(maxsulot, '', concat(NEW.name, '*', new.quantity,'*', NEW.cost, ', '))
  where reportOmbor.i_id = new.invoice_id
;

create table production2
(
	id int auto_increment
		primary key,
	barcode varchar(40) null,
	name varchar(200) null,
	type int(10) null,
	quantity int(10) null,
	p_name varchar(100) null,
	p_barcode varchar(100) null,
	color varchar(200) null,
	p_quantity int(10) null,
	date varchar(100) not null,
	user_id int not null
)
;

create table production2_ready
(
	id int auto_increment
		primary key,
	barcode varchar(40) null,
	name varchar(200) null,
	type varchar(100) null,
	quantity int(10) null,
	color varchar(200) null,
	date varchar(100) not null,
	user_id int not null,
	constraint production2_ready_barcode_uindex
		unique (barcode)
)
;

create table production2_tarix
(
	id int auto_increment
		primary key,
	barcode varchar(40) null,
	name varchar(200) null,
	type int(10) null,
	quantity int(10) null,
	p_name varchar(100) null,
	p_barcode varchar(100) null,
	color varchar(200) null,
	p_quantity int(10) null,
	date varchar(100) not null,
	user_id int not null
)
;

create table production2_xarajat
(
	id int auto_increment
		primary key,
	barcode varchar(100) null,
	name varchar(400) null,
	type varchar(400) null,
	quantity int null,
	cr_on varchar(200) null,
	cr_by varchar(200) null
)
;

create table production3
(
	id int auto_increment
		primary key,
	barcode varchar(200) null,
	name varchar(300) null,
	type int null,
	color int null,
	quantity int null,
	pBarcode varchar(200) null,
	pName varchar(300) null,
	pCost decimal(20,2) null,
	pQuantity int null,
	pColor int null,
	dBarcode varchar(200) null,
	dName varchar(300) null,
	dQuantity int null,
	cr_on varchar(200) null,
	cr_by int null,
	ready int default '0' null
)
;

create table report1
(
	id int auto_increment
		primary key,
	type varchar(100) null,
	who int null,
	sum decimal(20,2) default '0.00' null,
	dollar decimal(20,2) default '0.00' null,
	hr decimal(20,2) default '0.00' null,
	psum decimal(20,2) default '0.00' null,
	pdollar decimal(20,2) default '0.00' null,
	phr decimal(20,2) default '0.00' null,
	sale decimal(20,2) default '0.00' null,
	product varchar(1000) null,
	comment varchar(500) null,
	cr_on varchar(100) null,
	cr_by int null,
	s_id int default '0' not null,
	sb_id int null,
	constraint report1_sb_id_uindex
		unique (sb_id)
)
;

create table reportombor
(
	id int auto_increment
		primary key,
	type varchar(200) null,
	invoiceName varchar(300) null,
	company varchar(300) null,
	account varchar(200) null,
	contract varchar(200) null,
	dollar decimal(20,2) null,
	sum decimal(20,2) null,
	hr decimal(20,2) null,
	maxsulot varchar(2000) null,
	izoh varchar(500) null,
	cr_on varchar(100) null,
	cr_by varchar(100) null,
	i_id int null,
	constraint reportombor_id_uindex
		unique (id),
	constraint reportombor_i_id_uindex
		unique (i_id)
)
;

create table sale_balance
(
	id int auto_increment
		primary key,
	type varchar(100) null,
	who int null,
	sum decimal(20,2) default '0.00' null,
	dollar decimal(20,2) default '0.00' null,
	hr decimal(20,2) default '0.00' null,
	description varchar(400) null,
	cr_by int null,
	date varchar(40) null,
	currency varchar(20) null,
	percentage varchar(45) null,
	sub_total decimal(20,2) null
)
;

create trigger insert_report1_saleBalance
after INSERT on sale_balance
for each row
insert into report1 (type, who, sum, dollar, hr, psum, pdollar, phr, sale, product, comment, cr_on, cr_by, sb_id)
  values (NEW.type, new.who, 0, 0, 0, NEW.sum, NEW.dollar, NEW.hr,  0, ':', new.description, new.date, new.cr_by, new.id)
;

create trigger sale_balance_update_who_balance
after INSERT on sale_balance
for each row
if new.type='Chiqim' then
  update balance set sum_in=sum_in + NEW.sum, dollar_in=dollar_in + NEW.dollar, hr_in=hr_in + NEW.hr where who=NEW.who;
  else
    update balance set sum_out=sum_out + NEW.sum, dollar_out=dollar_out + NEW.dollar, hr_out=hr_out + NEW.hr where who=NEW.who;
  end if
;

create table sell
(
	id int auto_increment
		primary key,
	barcode varchar(100) not null,
	type_id int(8) null,
	name varchar(100) null,
	quantity int null,
	cost decimal(15,2) null,
	unit int null,
	description varchar(500) not null,
	date varchar(100) null,
	cr_by int null,
	constraint barcode_UNIQUE
		unique (barcode)
)
;

create table sellaction
(
	id int auto_increment
		primary key,
	sum decimal(20,2) null,
	dollar decimal(20,2) null,
	hr decimal(20,2) null,
	sale decimal(20,2) null,
	customer_id int null,
	cr_by int null,
	date_cr varchar(20) null,
	comment varchar(400) null,
	psum decimal(20,2) null,
	pdollar decimal(20,2) null,
	phr decimal(20,2) null
)
;

create trigger insert_report1_sellAction
after INSERT on sellaction
for each row
insert into report1 ( type, who, sum, dollar, hr, psum, pdollar, phr, sale, product, comment, cr_on, cr_by, s_id) values ('Savdo', new.customer_id,
  NEW.sum, NEW.dollar, NEW.hr, new.psum, new.pdollar, new.phr, new.sale, ':', new.comment, new.date_cr, new.cr_by, new.id)
;

create trigger updateBalance
after INSERT on sellaction
for each row
update balance set sum_in= sum_in+ new.sum, dollar_in = dollar_in+ new.dollar, hr_in = hr_in + new.hr, sum_out= sum_out+ new.psum, dollar_out= dollar_out+new.pdollar
  , hr_out =hr_out + new.phr where who = new.customer_id
;

create table sellaction_r
(
	id int auto_increment
		primary key,
	sum decimal(20,2) null,
	dollar decimal(20,2) null,
	hr decimal(20,2) null,
	sale decimal(20,2) null,
	customer_id int null,
	cr_by int null,
	date_cr varchar(20) null,
	psum decimal(20,2) null,
	pdollar decimal(20,2) null,
	phr decimal(20,2) null
)
;

create trigger revertBalance
after INSERT on sellaction_r
for each row
update balance set sum_in=sum_in-new.sum, dollar_in=dollar_in-new.dollar, hr_in=hr_in-new.hr, sum_out=sum_out-new.psum, dollar_out=dollar_out-new.pdollar, hr_out= hr_out-new.phr
  where who = new.customer_id
;

create table sellupdatelog
(
	id int auto_increment
		primary key,
	name varchar(100) null,
	quantity int null,
	cost decimal(20,2) null,
	p_id int null,
	cr_on varchar(45) null,
	cr_by int null
)
;

create table shartnoma
(
	id int auto_increment
		primary key,
	company varchar(100) null,
	name varchar(100) null,
	date varchar(100) null
)
;

create table t_balance_rate
(
	id int auto_increment
		primary key,
	cr_on varchar(100) null,
	cost decimal(20,2) null
)
;

create table total_balance
(
	id int(1) auto_increment
		primary key,
	sum decimal(20,2) null,
	dollar decimal(20,2) null,
	hr decimal(20,2) null,
	vhr decimal(20,2) null
)
;

create table type
(
	id int auto_increment
		primary key,
	name varchar(100) null,
	unit tinyint(1) default '1' null,
	info varchar(300) null,
	date_cr varchar(40) null,
	cr_by int null,
	constraint type_name_uindex
		unique (name)
)
;

create table user
(
	id int auto_increment
		primary key,
	username varchar(20) null,
	firstname varchar(20) null,
	lastname varchar(20) null,
	password varchar(20) null,
	userType int(3) null,
	phone varchar(15) null,
	path varchar(300) default '0' null,
	constraint user_username_uindex
		unique (username)
)
;

create table utils
(
	printerName varchar(300) null,
	filePath varchar(300) null,
	dollar varchar(10) null
)
;

create view accountexpense_v as select
    `a`.`id`            AS `id`,
    `a`.`schot`         AS `schot`,
    `a`.`schotDate`     AS `schotDate`,
    `p`.`companyName`   AS `firmaName`,
    `a`.`shartnoma`     AS `shartnoma`,
    `a`.`hr`            AS `hr`,
    `a`.`dollar`        AS `dollar`,
    `a`.`izoh`          AS `izoh`,
    `a`.`shartnomaDate` AS `shartnomaDate`
  from (`sbd_factory`.`accountexpense` `a`
    join `sbd_factory`.`person` `p` on ((`a`.`firmaName` = `p`.`id`)))
;

create view admin_history_v as select
    `sbd_factory`.`log`.`id`        AS `id`,
    `sbd_factory`.`log`.`module`    AS `module`,
    `sbd_factory`.`log`.`type`      AS `type`,
    `sbd_factory`.`log`.`cost`      AS `cost`,
    `sbd_factory`.`user`.`username` AS `cr_by`,
    `sbd_factory`.`log`.`date`      AS `date`,
    `sbd_factory`.`log`.`comment`   AS `comment`,
    `sbd_factory`.`log`.`summa`     AS `summa`
  from (`sbd_factory`.`log`
    join `sbd_factory`.`user` on ((`sbd_factory`.`log`.`cr_by` = `sbd_factory`.`user`.`id`)))
;

create view balance_v as select
    `b`.`id`                             AS `id`,
    `p`.`companyName`                    AS `who`,
    `b`.`sum_in`                         AS `sum_in`,
    `b`.`sum_out`                        AS `sum_out`,
    (`b`.`sum_out` - `b`.`sum_in`)       AS `sum_balance`,
    `b`.`dollar_in`                      AS `dollar_in`,
    `b`.`dollar_out`                     AS `dollar_out`,
    (`b`.`dollar_out` - `b`.`dollar_in`) AS `dollar_balance`,
    `b`.`hr_in`                          AS `hr_in`,
    `b`.`hr_out`                         AS `hr_out`,
    (`b`.`hr_out` - `b`.`hr_in`)         AS `hr_balance`
  from (`sbd_factory`.`balance` `b`
    join `sbd_factory`.`person` `p` on ((`b`.`who` = `p`.`id`)))
  where (`p`.`type` <> 3)
;

create view balancetotal_v as select
    (select sum((`sbd_factory`.`product`.`quantity` * `sbd_factory`.`product`.`cost`)) AS `total_cost`
     from `sbd_factory`.`product`)                    AS `product`,
    (select sum((`sbd_factory`.`production2_ready`.`quantity` * 5)) AS `total_p1`
     from `sbd_factory`.`production2_ready`)          AS `p2`,
    (select sum((`sbd_factory`.`production3`.`quantity` * 30)) AS `total_p3`
     from `sbd_factory`.`production3`
     where (`sbd_factory`.`production3`.`ready` = 0)) AS `p3`,
    (select sum((`sbd_factory`.`sell`.`quantity` * `sbd_factory`.`sell`.`cost`)) AS `total_sell`
     from `sbd_factory`.`sell`)                       AS `sell`,
    (select `sbd_factory`.`total_balance`.`sum`
     from `sbd_factory`.`total_balance`)              AS `sum`,
    (select `sbd_factory`.`total_balance`.`dollar`
     from `sbd_factory`.`total_balance`)              AS `dollar`,
    (select `sbd_factory`.`total_balance`.`hr`
     from `sbd_factory`.`total_balance`)              AS `hr`,
    (select `sbd_factory`.`total_balance`.`vhr`
     from `sbd_factory`.`total_balance`)              AS `vhr`,
    (((((select sum((`sbd_factory`.`product`.`quantity` * `sbd_factory`.`product`.`cost`)) AS `total_cost`
         from `sbd_factory`.`product`) + (select sum((`sbd_factory`.`production2_ready`.`quantity` * 5)) AS `total_p1`
                                          from `sbd_factory`.`production2_ready`)) +
       (select sum((`sbd_factory`.`sell`.`quantity` * `sbd_factory`.`sell`.`cost`)) AS `total_sell`
        from `sbd_factory`.`sell`)) + (select `sbd_factory`.`total_balance`.`dollar`
                                       from `sbd_factory`.`total_balance`)) +
     (select `sbd_factory`.`total_balance`.`vhr`
      from `sbd_factory`.`total_balance`))            AS `total_all`
;

create view debtors_v as select
    `b`.`id`                             AS `id`,
    `p`.`companyName`                    AS `who`,
    `b`.`sum_in`                         AS `sum_in`,
    `b`.`sum_out`                        AS `sum_out`,
    (`b`.`sum_out` - `b`.`sum_in`)       AS `sum_balance`,
    `b`.`dollar_in`                      AS `dollar_in`,
    `b`.`dollar_out`                     AS `dollar_out`,
    (`b`.`dollar_out` - `b`.`dollar_in`) AS `dollar_balance`,
    `b`.`hr_in`                          AS `hr_in`,
    `b`.`hr_out`                         AS `hr_out`,
    (`b`.`hr_out` - `b`.`hr_in`)         AS `hr_balance`
  from (`sbd_factory`.`balance` `b`
    join `sbd_factory`.`person` `p` on ((`b`.`who` = `p`.`id`)))
  where (((`b`.`sum_out` - `b`.`sum_in`) < 0) or ((`b`.`dollar_out` - `b`.`dollar_in`) < 0) or
         ((`b`.`hr_out` - `b`.`hr_in`) < 0))
;

create view exchange_v_s as select
    `e`.`id`       AS `id`,
    `e`.`cr_on`    AS `cr_on`,
    `e`.`Name`     AS `name`,
    `e`.`barcode`  AS `barcode`,
    `e`.`type`     AS `type`,
    `e`.`quantity` AS `quantity`,
    `e`.`comment`  AS `comment`,
    `u`.`username` AS `cr_by`
  from (`sbd_factory`.`exchange_log` `e`
    join `sbd_factory`.`user` `u` on ((`e`.`cr_by` = `u`.`id`)))
  where ((`e`.`comment` = '1-S') or (`e`.`comment` = 'O-S') or (`e`.`comment` = '2-S'))
;

create view history_v as select
    `h`.`id`                      AS `id`,
    `h`.`barcode`                 AS `barcode`,
    `h`.`p_id`                    AS `p_id`,
    `h`.`name`                    AS `name`,
    `h`.`type`                    AS `type`,
    `h`.`quantity`                AS `quantity`,
    `u`.`username`                AS `seller_id`,
    `h`.`cost`                    AS `cost`,
    (`h`.`cost` / `h`.`quantity`) AS `per_cost`,
    `h`.`date_cr`                 AS `date_cr`,
    `p`.`companyName`             AS `customer_id`,
    `h`.`sellAction_id`           AS `sellAction_id`
  from ((`sbd_factory`.`history` `h`
    join `sbd_factory`.`user` `u`) join `sbd_factory`.`person` `p`
      on (((`h`.`seller_id` = `u`.`id`) and (`h`.`customer_id` = `p`.`id`))))
;

create view log_v as select
    `sbd_factory`.`log`.`id`        AS `id`,
    `sbd_factory`.`log`.`module`    AS `module`,
    `sbd_factory`.`log`.`type`      AS `type`,
    `sbd_factory`.`log`.`cost`      AS `cost`,
    `sbd_factory`.`user`.`username` AS `cr_by`,
    `sbd_factory`.`log`.`date`      AS `date`,
    `sbd_factory`.`log`.`comment`   AS `comment`
  from (`sbd_factory`.`log`
    join `sbd_factory`.`user` on ((`sbd_factory`.`log`.`cr_by` = `sbd_factory`.`user`.`id`)))
;

create view main1_history_v as select
    `sbd_factory`.`log`.`id`        AS `id`,
    `sbd_factory`.`log`.`module`    AS `module`,
    `sbd_factory`.`log`.`type`      AS `type`,
    `sbd_factory`.`log`.`cost`      AS `cost`,
    `sbd_factory`.`user`.`username` AS `cr_by`,
    `sbd_factory`.`log`.`date`      AS `date`,
    `sbd_factory`.`log`.`comment`   AS `comment`
  from (`sbd_factory`.`log`
    join `sbd_factory`.`user` on ((`sbd_factory`.`log`.`cr_by` = `sbd_factory`.`user`.`id`)))
  where (`sbd_factory`.`log`.`module` = '1-ISH/CH')
;

create view main2_history_v as select
    `sbd_factory`.`log`.`id`        AS `id`,
    `sbd_factory`.`log`.`module`    AS `module`,
    `sbd_factory`.`log`.`type`      AS `type`,
    `sbd_factory`.`log`.`cost`      AS `cost`,
    `sbd_factory`.`user`.`username` AS `cr_by`,
    `sbd_factory`.`log`.`date`      AS `date`,
    `sbd_factory`.`log`.`comment`   AS `comment`
  from (`sbd_factory`.`log`
    join `sbd_factory`.`user` on ((`sbd_factory`.`log`.`cr_by` = `sbd_factory`.`user`.`id`)))
  where (`sbd_factory`.`log`.`module` = '2-ISH/CH')
;

create view manlist_v as select
    `m`.`id`          AS `id`,
    `m`.`name`        AS `name`,
    `m`.`barcode`     AS `barcode`,
    `t`.`name`        AS `type`,
    `m`.`description` AS `description`
  from (`sbd_factory`.`manlist` `m`
    join `sbd_factory`.`type` `t` on ((`m`.`type` = `t`.`id`)))
;

create view marketing_v as select
    `m`.`id`          AS `id`,
    `s`.`companyName` AS `company`,
    `m`.`name`        AS `name`,
    `m`.`barcode_o`   AS `barcode_o`,
    `m`.`barcode`     AS `barcode`,
    `m`.`color`       AS `color`,
    `m`.`cost`        AS `cost`
  from (`sbd_factory`.`marketing_filter` `m`
    join `sbd_factory`.`person` `s` on ((`m`.`company` = `s`.`id`)))
;

create view product_h_v as select
    `p`.`id`                      AS `id`,
    `p`.`oper_type`               AS `oper_type`,
    `p`.`invoice_id`              AS `invoice`,
    (case `p`.`unit`
     when '1'
       then 'Dona'
     when '2'
       then 'Kg'
     when '3'
       then 'Rulon'
     when '4'
       then 'Litr'
     when '5'
       then 'm2' end)             AS `unit`,
    `p`.`barcode`                 AS `barcode`,
    `p`.`name`                    AS `name`,
    `p`.`type`                    AS `type`,
    `p`.`cost`                    AS `cost`,
    `p`.`quantity`                AS `quantity`,
    (`p`.`quantity` * `p`.`cost`) AS `total_cost`,
    `s`.`companyName`             AS `suplier`,
    `p`.`date_cr`                 AS `date_cr`,
    `u`.`username`                AS `user`,
    `p`.`description`             AS `description`,
    `c`.`name`                    AS `color`
  from ((((`sbd_factory`.`product_h` `p`
    join `sbd_factory`.`person` `s`) join `sbd_factory`.`user` `u`) join `sbd_factory`.`invoice` `i`) join
    `sbd_factory`.`color` `c`
      on (((`p`.`cr_by` = `u`.`id`) and (`p`.`suplier_id` = `s`.`id`) and (`p`.`invoice_id` = `i`.`id`) and
           (`p`.`color` = `c`.`id`))))
;

create view product_history_v as select
    `sbd_factory`.`log`.`id`        AS `id`,
    `sbd_factory`.`log`.`module`    AS `module`,
    `sbd_factory`.`log`.`type`      AS `type`,
    `sbd_factory`.`log`.`cost`      AS `cost`,
    `sbd_factory`.`user`.`username` AS `cr_by`,
    `sbd_factory`.`log`.`date`      AS `date`,
    `sbd_factory`.`log`.`comment`   AS `comment`
  from (`sbd_factory`.`log`
    join `sbd_factory`.`user` on ((`sbd_factory`.`log`.`cr_by` = `sbd_factory`.`user`.`id`)))
  where (`sbd_factory`.`log`.`module` = 'Ombor')
;

create view product_v as select
    `p`.`id`                      AS `id`,
    `i`.`name`                    AS `invoice`,
    (case `p`.`unit`
     when '1'
       then 'Dona'
     when '2'
       then 'Kg'
     when '3'
       then 'Rulon'
     when '4'
       then 'Litr'
     when '5'
       then 'm2' end)             AS `unit`,
    `p`.`barcode`                 AS `barcode`,
    `p`.`name`                    AS `name`,
    `p`.`type`                    AS `type`,
    `p`.`cost`                    AS `cost`,
    `p`.`quantity`                AS `quantity`,
    (`p`.`quantity` * `p`.`cost`) AS `total_cost`,
    `s`.`companyName`             AS `suplier`,
    `p`.`date_cr`                 AS `date_cr`,
    `u`.`username`                AS `user`,
    `p`.`description`             AS `description`,
    `c`.`name`                    AS `color`
  from ((((`sbd_factory`.`product` `p`
    join `sbd_factory`.`person` `s`) join `sbd_factory`.`user` `u`) join `sbd_factory`.`invoice` `i`) join
    `sbd_factory`.`color` `c`
      on (((`p`.`cr_by` = `u`.`id`) and (`p`.`suplier_id` = `s`.`id`) and (`p`.`invoice_id` = `i`.`id`) and
           (`p`.`color` = `c`.`id`))))
;

create view production2_v as select
    `p`.`id`         AS `id`,
    `p`.`barcode`    AS `barcode`,
    `p`.`name`       AS `name`,
    `p`.`type`       AS `type`,
    `p`.`quantity`   AS `quantity`,
    `p`.`p_name`     AS `p_name`,
    `p`.`p_barcode`  AS `p_barcode`,
    `p`.`color`      AS `color`,
    `p`.`p_quantity` AS `p_quantity`,
    `p`.`date`       AS `date`,
    `p`.`user_id`    AS `user_id`
  from (`sbd_factory`.`production2` `p`
    join `sbd_factory`.`type` `t` on ((`p`.`type` = `t`.`id`)))
  where (`p`.`p_quantity` > 0)
;

create view production3_v as select
    `p`.`id`        AS `id`,
    `p`.`barcode`   AS `barcode`,
    `p`.`name`      AS `name`,
    `p`.`type`      AS `type`,
    `p`.`color`     AS `color`,
    `p`.`quantity`  AS `quantity`,
    `p`.`pBarcode`  AS `pBarcode`,
    `p`.`pName`     AS `pName`,
    `p`.`pCost`     AS `pCost`,
    `p`.`pQuantity` AS `pQuantity`,
    `p`.`pColor`    AS `pColor`,
    `p`.`dBarcode`  AS `dBarcode`,
    `p`.`dName`     AS `dName`,
    `p`.`dQuantity` AS `dQuantity`,
    `p`.`cr_on`     AS `cr_on`,
    `p`.`cr_by`     AS `cr_by`
  from `sbd_factory`.`production3` `p`
  where (`p`.`ready` = 0)
;

create view report1_v as select
    `r`.`id`          AS `id`,
    `r`.`type`        AS `type`,
    `p`.`companyName` AS `who`,
    `r`.`sum`         AS `sum`,
    `r`.`dollar`      AS `dollar`,
    `r`.`hr`          AS `hr`,
    `r`.`psum`        AS `psum`,
    `r`.`pdollar`     AS `pdollar`,
    `r`.`phr`         AS `phr`,
    `r`.`sale`        AS `sale`,
    `r`.`product`     AS `product`,
    `r`.`comment`     AS `comment`,
    `r`.`cr_on`       AS `cr_on`,
    `r`.`s_id`        AS `s_id`
  from (`sbd_factory`.`report1` `r`
    join `sbd_factory`.`person` `p` on ((`r`.`who` = `p`.`id`)))
;

create view sale_balance_v as select
    `s`.`id`          AS `id`,
    `s`.`type`        AS `type`,
    `p`.`companyName` AS `who`,
    `s`.`sum`         AS `sum`,
    `s`.`dollar`      AS `dollar`,
    `s`.`hr`          AS `hr`,
    `s`.`description` AS `description`,
    `u`.`username`    AS `cr_by`,
    `s`.`date`        AS `date`,
    `s`.`currency`    AS `currency`,
    `s`.`percentage`  AS `percentage`,
    `s`.`sub_total`   AS `sub_total`
  from ((`sbd_factory`.`sale_balance` `s`
    join `sbd_factory`.`person` `p`) join `sbd_factory`.`user` `u`
      on (((`s`.`who` = `p`.`id`) and (`s`.`cr_by` = `u`.`id`))))
;

create view savdo_history_v as select
    `sbd_factory`.`log`.`id`        AS `id`,
    `sbd_factory`.`log`.`module`    AS `module`,
    `sbd_factory`.`log`.`type`      AS `type`,
    `sbd_factory`.`log`.`cost`      AS `cost`,
    `sbd_factory`.`user`.`username` AS `cr_by`,
    `sbd_factory`.`log`.`date`      AS `date`,
    `sbd_factory`.`log`.`comment`   AS `comment`
  from (`sbd_factory`.`log`
    join `sbd_factory`.`user` on ((`sbd_factory`.`log`.`cr_by` = `sbd_factory`.`user`.`id`)))
  where (`sbd_factory`.`log`.`module` = 'Savdo')
;

create view sell_v as select
    `s`.`id`          AS `id`,
    `s`.`barcode`     AS `barcode`,
    `t`.`name`        AS `type`,
    `s`.`name`        AS `name`,
    `s`.`quantity`    AS `quantity`,
    `s`.`cost`        AS `cost`,
    (case `s`.`unit`
     when '1'
       then 'Dona'
     when '2'
       then 'Kg'
     when '3'
       then 'Rulon'
     when '4'
       then 'Litr'
     when '5'
       then 'm2' end) AS `unit`,
    `s`.`date`        AS `date`,
    `u`.`username`    AS `username`,
    `s`.`description` AS `description`
  from ((`sbd_factory`.`sell` `s`
    join `sbd_factory`.`type` `t`) join `sbd_factory`.`user` `u`
      on (((`s`.`type_id` = `t`.`id`) and (`s`.`cr_by` = `u`.`id`))))
;

create view sellaction_v as select
    `s`.`id`          AS `id`,
    `s`.`sum`         AS `sum`,
    `s`.`dollar`      AS `dollar`,
    `s`.`hr`          AS `hr`,
    `s`.`psum`        AS `psum`,
    `s`.`pdollar`     AS `pdollar`,
    `s`.`phr`         AS `phr`,
    `s`.`sale`        AS `sale`,
    `p`.`companyName` AS `companyName`,
    `u`.`username`    AS `username`,
    `s`.`date_cr`     AS `date_cr`,
    `s`.`comment`     AS `comment`
  from ((`sbd_factory`.`sellaction` `s`
    join `sbd_factory`.`person` `p`) join `sbd_factory`.`user` `u`
      on (((`s`.`customer_id` = `p`.`id`) and (`s`.`cr_by` = `u`.`id`))))
;

create view total_balance_v as select
    (select sum((`sbd_factory`.`product`.`cost` * `sbd_factory`.`product`.`quantity`))
     from `sbd_factory`.`product`)                  AS `total_product`,
    (select sum((3 * `sbd_factory`.`production2_ready`.`quantity`))
     from `sbd_factory`.`production2_ready`)        AS `total_product_walpaper`,
    (select (`sbd_factory`.`balance`.`sum_out` - `sbd_factory`.`balance`.`sum_in`)
     from `sbd_factory`.`balance`
     where (`sbd_factory`.`balance`.`who` = 99999)) AS `C_Sum`,
    (select (`sbd_factory`.`balance`.`dollar_out` - `sbd_factory`.`balance`.`dollar_in`)
     from `sbd_factory`.`balance`
     where (`sbd_factory`.`balance`.`who` = 99999)) AS `C_dollar`,
    (select (`sbd_factory`.`balance`.`hr_out` - `sbd_factory`.`balance`.`hr_in`)
     from `sbd_factory`.`balance`
     where (`sbd_factory`.`balance`.`who` = 99999)) AS `C_hr`,
    (select sum((`sbd_factory`.`sell`.`cost` * `sbd_factory`.`sell`.`quantity`))
     from `sbd_factory`.`sell`)                     AS `total_sell`,
    (select `sbd_factory`.`total_balance`.`sum`
     from `sbd_factory`.`total_balance`)            AS `total_sum`,
    (select `sbd_factory`.`total_balance`.`dollar`
     from `sbd_factory`.`total_balance`)            AS `total_dollar`,
    (select `sbd_factory`.`total_balance`.`hr`
     from `sbd_factory`.`total_balance`)            AS `total_hr`,
    (select `sbd_factory`.`total_balance`.`vhr`
     from `sbd_factory`.`total_balance`)            AS `total_vhr`
;

create view type_v as select
    `t`.`id`          AS `id`,
    `t`.`name`        AS `name`,
    `t`.`info`        AS `info`,
    `t`.`date_cr`     AS `date_cr`,
    `u`.`username`    AS `cr_by`,
    (case `t`.`unit`
     when '1'
       then 'Dona'
     when '2'
       then 'Kg'
     when '3'
       then 'Rulon'
     when '4'
       then 'Litr'
     when '5'
       then 'm2' end) AS `unit`
  from (`sbd_factory`.`type` `t`
    join `sbd_factory`.`user` `u`)
  where (`t`.`cr_by` = `u`.`id`)
;

create view user_v as select
    `u`.`id`                  AS `id`,
    `u`.`username`            AS `username`,
    `u`.`firstname`           AS `firstname`,
    `u`.`lastname`            AS `lastname`,
    `u`.`password`            AS `password`,
    `u`.`phone`               AS `phone`,
    (case `u`.`userType`
     when '1'
       then 'Ombor'
     when '2'
       then '1-ish/ch'
     when '3'
       then '2-ish/ch'
     when '4'
       then 'Savdo'
     when '5'
       then 'Admin'
     when '6'
       then 'Accounting' end) AS `userType`
  from `sbd_factory`.`user` `u`
  order by `u`.`id`
;

create procedure addSuplier (IN companyName varchar(100))
BEGIN
    insert into person (type, companyName, account, phone, info, cr_by, date_cr) VALUES( 1, companyName, 1, 1, 1,1, sysdate());
END
;

