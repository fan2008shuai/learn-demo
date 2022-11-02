

create table t_user
(
       id bigint(20) not null auto_increment,
       user_name varchar(60) not null,
       sex tinyint(3) not null,
       mobile varchar(20) not null,
       email varchar(60) not null,
       note varchar(1024),
       primary key (id)
);


alter table t_user modify sex varchar(20) not null;

select columns from t_user;