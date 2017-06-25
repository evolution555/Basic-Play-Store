# --- Created by Ebean DDL
# To stop Ebean DDL generation, remove this comment and start using Evolutions

# --- !Ups

create table items (
  item_id                       varchar(255) not null,
  title                         varchar(255),
  cost                          double,
  description                   varchar(255),
  catagory                      varchar(255),
  constraint pk_items primary key (item_id)
);
create sequence id_gen;

create table user (
  email                         varchar(255) not null,
  name                          varchar(255),
  role                          varchar(255),
  password                      varchar(255),
  constraint pk_user primary key (email)
);


# --- !Downs

drop table if exists items;
drop sequence if exists id_gen;

drop table if exists user;

