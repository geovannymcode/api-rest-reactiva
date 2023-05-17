create schema if not exists app;
create table if not exists app.school(
       id serial not null primary key,
       name varchar(255) not null,
       address varchar(255) not null,
       email varchar(255) not null unique
);
create table if not exists application.student(
       id serial not null primary key,
       firstname varchar(255) not null,
       lastname varchar(255) not null,
       email varchar(255) not null unique,
       age int not null,
       school_id bigint not null references app.school(id) on delete cascade
);