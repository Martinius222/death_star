 create table if not exists operationtroopers(
    id SERIAL
    constraint operationstroopers_pk
            primary key,
	name varchar(100),
	email varchar(100),
	operations_id int
);