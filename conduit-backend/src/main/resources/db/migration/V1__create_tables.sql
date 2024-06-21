-- V1__CreateUsersTable.sql
create table users
(
    id       integer primary key autoincrement,
    email    varchar(255) not null unique,
    username varchar(255) not null unique,
    password varchar(255) not null,
    bio      varchar(4096),
    image    varchar(255)
);
