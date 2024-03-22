create table user (
    id bigint primary key auto_increment,
    rol varchar(50) not null,
    username varchar(50) not null unique,
    password varchar(250) not null
    );