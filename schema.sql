create table bet
(
    id          bigint      not null
        primary key,
    user        bigint      not null,
    startDate   date        not null,
    endDate     date        null,
    description varchar(40) null
);

create table hibernate_sequence
(
    next_val bigint null
);

create table stream
(
    id   bigint       not null
        primary key,
    name varchar(30)  not null,
    url  varchar(300) not null
);

create table team
(
    name  varchar(30) not null,
    wins  int         null,
    loses int         null,
    id    bigint      not null
        primary key
);

create table `match`
(
    id          bigint       not null
        primary key,
    team1       bigint       not null,
    team2       bigint       not null,
    date        datetime     not null,
    description varchar(300) not null,
    result      varchar(45)  null,
    stream      bigint       null,
    constraint TeamMatch2___fk
        foreign key (team2) references team (id),
    constraint TeamMatch___fk
        foreign key (team1) references team (id),
    constraint stream___fk
        foreign key (stream) references stream (id)
);

create table bet_match
(
    id      bigint not null
        primary key,
    `match` bigint not null,
    bet     bigint not null,
    constraint bet_match_bet_id_fk
        foreign key (bet) references bet (id),
    constraint bet_match_match_id_fk
        foreign key (`match`) references `match` (id)
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

