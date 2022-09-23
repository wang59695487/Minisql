create table student2
	(id int, name char(6) unique, score float, primary key (id));
insert into student2 values(1080100001,'D',99);
insert into student2 values(1080100002,'C',52.5);
insert into student2 values(1080100003,'E',98.5);
insert into student2 values(1080100004,'A',91.5);
insert into student2 values(1080100005,'G',72.5);
insert into student2 values(1080100006,'F',89.5);
insert into student2 values(1080100007,'I',63);
insert into student2 values(1080100008,'H',73.5);
insert into student2 values(1080100009,'J',79.5);
insert into student2 values(1080100010,'B',70.5);


select * from student2 where id<=1080100005 order  by name ;
drop table student2;

