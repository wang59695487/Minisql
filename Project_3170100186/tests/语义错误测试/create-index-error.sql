create table person (id int unique, x int unique, y int unique,z int,primary key(id));

create index idx_y on person(y);

create index idx_y on person(x);
create index idx_x on peson(x);
create index idx_x on person(xx);
create index idx_x on person(id);
create index idx_x on person(y);
create index idx_x on person(z);