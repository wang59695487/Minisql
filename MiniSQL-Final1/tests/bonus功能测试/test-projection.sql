create table student2
	(id int, name char(6) unique, score float, primary key (id));
insert into student2 values(1080100001,'杨豪琛',99);
insert into student2 values(1080100002,'楼河加',52.5);
insert into student2 values(1080100003,'袁河辉',98.5);
insert into student2 values(1080100004,'陈明分',91.5);
insert into student2 values(1080100005,'劳 昊',72.5);
insert into student2 values(1080100006,'袁帅超',89.5);
insert into student2 values(1080100007,'殷月月',63);
insert into student2 values(1080100008,'袁程亚',73.5);
insert into student2 values(1080100009,'申辉幸',79.5);
insert into student2 values(1080100010,'马云程',70.5);

select name from student2 where id=1080100001;

drop table student2;