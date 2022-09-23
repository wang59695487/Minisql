create table student2
	(id int, name char(6) unique, score float, primary key (id));
insert into student2 values(1080100001,'陈刚',99);
insert into student2 values(1080100002,'竺可桢',52.5);
insert into student2 values(1080100003,'张国荣',98.5);
insert into student2 values(1080100004,'陈道明',91.5);
insert into student2 values(1080100005,'吴朝晖',72.5);
insert into student2 values(1080100006,'马超',89.5);
insert into student2 values(1080100007,'芈月',63);
insert into student2 values(1080100008,'程咬金',73.5);
insert into student2 values(1080100009,'嬴政',79.5);
insert into student2 values(1080100010,'Saber',70.5);


select * from student2 order by score;
drop table student2;

