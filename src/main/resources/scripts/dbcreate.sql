-- LEVEL
create table if not exists level
(
  id bigint not null
    constraint level_pk
      primary key,
  name varchar(60)
);

alter table level owner to postgres;

-- WORKSHOP
create table if not exists workshop
(
  workshop bigint not null
    constraint workshop_pk
      primary key,
  name varchar(60),
  address varchar(60),
  open_time time,
  close_time time,
  owner_name varchar(60)
);

alter table workshop owner to postgres;

-- MASTER
create table if not exists master
(
  master_passport_num bigint not null
    constraint master_pk
      primary key,
  name varchar(60),
  phone_num integer,
  level bigint
    constraint level_fk
      references level,
  workshop bigint
    constraint inn_fk
      references workshop
);

alter table master owner to postgres;

create unique index master_phone_num_uindex
  on master (phone_num);



-- CUSTOMER
create table if not exists customer
(
  customer_passport_num bigint not null
    constraint customer_pk
      primary key,
  name varchar(60),
  phone_num integer,
  address varchar(60),
  birth_date date,
  workshop bigint
    constraint inn_fk
      references workshop
);

alter table customer owner to postgres;

create unique index customer_phone_num_uindex
  on customer (phone_num);

-- CAR
create table if not exists car
(
  car_number varchar(10) not null
    constraint car_pk
      primary key,
  mark varchar(60),
  model varchar(60),
  mileage integer,
  customer_passport_num bigint
    constraint customer_passport_num_fk
      references customer,
  master_passport_num bigint
    constraint master_passport_num_fk
      references master,
  crash_type varchar(60)
);

alter table car owner to postgres;

create unique index car_car_number_uindex
  on car (car_number);