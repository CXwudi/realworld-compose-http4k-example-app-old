-- V1__CreateUsersTable.sql
create table users (
   id integer primary key auto_increment,
   email varchar(255) not null unique,
   username varchar(255) not null unique,
   password varchar(255) not null,
   image varchar(255)
);
