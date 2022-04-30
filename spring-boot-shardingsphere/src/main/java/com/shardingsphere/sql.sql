create database course_db;

use course_db;

create table course_1 (
                          cid bigint(20) primary key ,
                          cname varchar(50) not null,
                          user_id bigint(20) not null ,
                          status varchar(10) not null
) engine = InnoDB;

create table course_2 (
                          cid bigint(20) primary key ,
                          cname varchar(50) not null,
                          user_id bigint(20) not null ,
                          status varchar(10) not null
) engine = InnoDB;





create database edu_db_1;
create database edu_db_2;

use edu_db_1;

create table course_1 (
                          `cid` bigint(20) primary key,
                          `cname` varchar(50) not null,
                          `user_id` bigint(20) not null,
                          `status` varchar(10) not null
);

create table course_2 (
                          `cid` bigint(20) primary key,
                          `cname` varchar(50) not null,
                          `user_id` bigint(20) not null,
                          `status` varchar(10) not null
);

use edu_db_2;

create table course_1 (
                          `cid` bigint(20) primary key,
                          `cname` varchar(50) not null,
                          `user_id` bigint(20) not null,
                          `status` varchar(10) not null
);

create table course_2 (
                          `cid` bigint(20) primary key,
                          `cname` varchar(50) not null,
                          `user_id` bigint(20) not null,
                          `status` varchar(10) not null
);

create database user_db;

use user_db;

create table t_user(
                       `user_id` bigint(20) primary key,
                       `username` varchar(100) not null,
                       `status` varchar(50) not null
);

# use user_db;
# use edu_db_1;
use edu_db_2;

create table t_dict(
                       `dict_id` bigint(20) primary key,
                       `status` varchar(100) not null,
                       `value` varchar(100) not null
);