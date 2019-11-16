create table if not exists Objectives(
    ID SERIAL UNIQUE PRIMARY KEY,
    Name varchar(100),
    Description varchar(1000)
)