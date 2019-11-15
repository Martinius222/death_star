create table if not exists operations(
    id SERIAL
    constraint operations_pk
        primary key,
    name varchar(100),
    description varchar(1000)
)