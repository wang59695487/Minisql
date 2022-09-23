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


create table department
	(depart_id int, de_name char(12) , primary key (depart_id));
insert into department values(1,'计算机学院');
insert into department values(2,'环境学院');
insert into department values(3,'表演学院');
insert into department values(4,'电竞学院');


select * from student2 join	department where student2.depart_id = department.depart_id;

drop table student2;
drop table department;

