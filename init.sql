create table bet
(
    id          bigint      not null
        primary key,
    user        bigint      not null,
    startDate   date        not null,
    endDate     date        null,
    description varchar(40) null
);
