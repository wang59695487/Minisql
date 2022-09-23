create table student2
	(id int, depart_id int,name char(6) unique, score float, primary key (id));
insert into student2 values(1080100001,1,'陈刚',99);
insert into student2 values(1080100002,2,'竺可桢',52.5);
insert into student2 values(1080100003,3,'张国荣',98.5);
insert into student2 values(1080100004,3,'陈道明',91.5);
insert into student2 values(1080100005,1,'吴朝晖',72.5);
insert into student2 values(1080100006,4,'马超',89.5);
insert into student2 values(1080100007,4,'芈月',63);
insert into student2 values(1080100008,4,'程咬金',73.5);
insert into student2 values(1080100009,4,'嬴政',79.5);
insert into student2 values(1080100010,4,'Saber',70.5);

select name,score from student2 where id>=1080100009 or score>=95 and depart_id=3 order by score;

drop table student2;
drop table department;

