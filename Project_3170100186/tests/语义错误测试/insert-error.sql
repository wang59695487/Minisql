create table person (id1 int unique,id2 int unique,primary key(id1));

insert into person values (1,2);

insert into peson values (2,3);
insert into person values (1,3);
insert into person values ('abc',5.0);
insert into person values (2,3,4);

drop table person;