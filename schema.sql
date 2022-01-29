create table hibernate_sequence
(
    next_val bigint null
);

create table user
(
    id         bigint       not null
        primary key,
    created_at datetime(6)  null,
    email      varchar(255) null,
    password   varchar(255) null,
    role       varchar(255) null,
    username   varchar(255) null,
    first_name varchar(20)  null,
    last_name  varchar(25)  null,
    birth_date date         null,
    amount     int          null
)
    charset = utf8mb3;

