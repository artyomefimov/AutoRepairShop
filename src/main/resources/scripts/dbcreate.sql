create database postgres
  with owner postgres;

comment on database postgres is 'default administrative connection database';

create sequence id_sequence;

alter sequence id_sequence owner to postgres;

create table level
(
  level_id bigint not null
    constraint level_pk
      primary key,
  name varchar(60)
);

alter table level owner to postgres;

create table workshop
(
  inn integer not null,
  name varchar(60),
  address varchar(60),
  open_time time,
  close_time time,
  owner_name varchar(60),
  workshop_id bigint not null
    constraint workshop_pk
      primary key
);

alter table workshop owner to postgres;

create table master
(
  master_passport_num integer not null,
  name varchar(60),
  phone_num integer,
  level_id bigint
    constraint level_id_fk
      references level,
  workshop_id bigint
    constraint workshop_id_fk
      references workshop,
  master_id bigint not null
    constraint master_pk
      primary key
);

alter table master owner to postgres;

create unique index master_phone_num_uindex
  on master (phone_num);

create table customer
(
  customer_passport_num integer not null,
  name varchar(60),
  phone_num integer,
  address varchar(60),
  birth_date date,
  workshop_id bigint
    constraint workshop_id_fk
      references workshop,
  customer_id bigint not null
    constraint customer_pk
      primary key
);

alter table customer owner to postgres;

create unique index customer_phone_num_uindex
  on customer (phone_num);

create table car
(
  car_number varchar(10) not null,
  mark varchar(60),
  model varchar(60),
  mileage integer,
  customer_id bigint
    constraint customer_id_fk
      references customer,
  master_id bigint
    constraint master_id_fk
      references master,
  crash_type varchar(60),
  car_id bigint not null
    constraint car_pk
      primary key
);

alter table car owner to postgres;

create unique index car_car_number_uindex
  on car (car_number);

create table "user"
(
  user_id bigint not null
    constraint user_pk
      primary key,
  login varchar(60) not null,
  password varchar(60) not null,
  role integer not null
);

alter table "user" owner to postgres;

create unique index user_login_uindex
  on "user" (login);

create unique index user_password_uindex
  on "user" (password);