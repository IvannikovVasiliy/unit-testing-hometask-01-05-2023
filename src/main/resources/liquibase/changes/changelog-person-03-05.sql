--liquibase formatted sql

--changeset ivannikov:neoflex6-studying-create-schemas-1 failOnError:true
CREATE SCHEMA test;

--changeset ivannikov:neoflex6-studying-create-table-public-user-1 failOnError:true
create table public.person (
    id bigserial primary key,
    name varchar(50) not null,
    email varchar(64) unique
);
--rollback drop table public.person;

--changeset ivannikov:neoflex6-studying-create-table-test-user-1 failOnError:true
create table test.person (
    id bigserial primary key,
    name varchar(50) not null,
    email varchar(64) unique
);
--rollback drop table test.person;

--changeset ivannikov:neoflex6-studying-insert-into-test-user-1 failOnError:true
--preCondition-sql-check expectedResult:0 SELECT COUNT(*) FROM test.person
--preconditions onError:CONTINUE
insert into test.person
values (1, 'Jack Daniels', 'jackdaniels@example.com'),
       (2, 'George Dickel', 'georgedickel@example.com'),
       (3, 'George Dickelson', 'georgedickelson@example.com');
--rollback delete from test.person;