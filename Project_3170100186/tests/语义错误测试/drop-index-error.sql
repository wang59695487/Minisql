create table person(x int unique,y int unique,primary key(x));

create index idx_y on person(y);

drop index idx_x;
drop index person_prikey;