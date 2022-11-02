

create table t_role
(
   id int(20) not null auto_increment,
   role_name varchar(60) not null,
   note varchar(1024),
   primary key (id)
);

insert into t_role(role_name, note) values ("test2","test2 mybatis"),("test3","test3 mybatis");