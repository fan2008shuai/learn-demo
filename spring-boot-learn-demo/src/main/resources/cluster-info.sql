create table cluster_info
(
       id bigint(20) not null auto_increment,
       app_key varchar(512),
       cluster_name varchar(512) not null,
       cluster_type varchar(512),
       redis_name varchar(512),
       mysql_name varchar(512),
       cluster_instance_count int,
       max_qpm_daily bigint(20),
       total_request_daily bigint(20),
       create_time datetime,
       update_time datetime,
       primary key (id)
);