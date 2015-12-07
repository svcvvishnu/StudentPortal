CREATE SEQUENCE student_pk START 1;
CREATE SEQUENCE course_pk START 1;
CREATE SEQUENCE faculty_pk START 1;
CREATE SEQUENCE staff_pk START 1;
CREATE SEQUENCE department_pk START 1;

CREATE TABLE student (
     sid    integer PRIMARY KEY DEFAULT nextval('student_pk'),
     sname   varchar(40),
     major   varchar(40),
     s_level   varchar(40),
     age integer
);


CREATE TABLE department (
     did    integer PRIMARY KEY DEFAULT nextval('department_pk'),
     dname   varchar(40)
);

CREATE TABLE staff (
     sid    integer PRIMARY KEY DEFAULT nextval('staff_pk'),
     sname   varchar(40),
     did   integer references department(did)
);

CREATE TABLE faculty (
     fid    integer PRIMARY KEY DEFAULT nextval('faculty_pk'),
     fname   varchar(40),
     did   integer references department(did)
);

CREATE TABLE courses (
     cid    integer PRIMARY KEY DEFAULT nextval('course_pk'),
     cname   varchar(40),
     meets_at   varchar(40),
     room   varchar(40),
     f_id   integer references faculty(fid),
     course_limit integer
);

CREATE TABLE enrolled (
     sid    integer ,
     cid    integer,
     exam1   integer,
     exam2   integer,
     final integer,
     PRIMARY KEY(sid, cid)
);

CREATE TABLE course_count ( 
     cid    integer references courses(cid),
     course_limit integer,
     enrolled_count integer
);

INSERT INTO student(sname,major,s_level,age) 
values('Student1_Name','Student1_Major','Student1_Level',25);
INSERT INTO student(sname,major,s_level,age) 
values('Student2_Name','Student2_Major','Student2_Level',26);
INSERT INTO student(sname,major,s_level,age) 
values('Student3_Name','Student3_Major','Student3_Level',27);
INSERT INTO student(sname,major,s_level,age) 
values('Student4_Name','Student4_Major','Student4_Level',28);
INSERT INTO student(sname,major,s_level,age) 
values('Student5_Name','Student5_Major','Student5_Level',29);
INSERT INTO student(sname,major,s_level,age) 
values('Student6_Name','Student6_Major','Student6_Level',21);
INSERT INTO student(sname,major,s_level,age) 
values('Student7_Name','Student7_Major','Student7_Level',22);
INSERT INTO student(sname,major,s_level,age) 
values('Student8_Name','Student8_Major','Student8_Level',23);
INSERT INTO student(sname,major,s_level,age) 
values('Student9_Name','Student9_Major','Student9_Level',24);
INSERT INTO student(sname,major,s_level,age) 
values('Student10_Name','Student10_Major','Student10_Level',20);

INSERT INTO department(dname) 
values('Department1');
INSERT INTO department(dname) 
values('Department2');
INSERT INTO department(dname) 
values('Department3');
INSERT INTO department(dname) 
values('Department4');
INSERT INTO department(dname) 
values('Department5');
INSERT INTO department(dname) 
values('Department6');
INSERT INTO department(dname) 
values('Department7');
INSERT INTO department(dname) 
values('Department8');
INSERT INTO department(dname) 
values('Department9');
INSERT INTO department(dname) 
values('Department10');


INSERT INTO Staff(sname,did) 
values('Staff',1);
INSERT INTO Staff(sname,did) 
values('Staff2',2);
INSERT INTO Staff(sname,did) 
values('Staff3',3);
INSERT INTO Staff(sname,did) 
values('Staff4',4);
INSERT INTO Staff(sname,did) 
values('Staff5',5);
INSERT INTO Staff(sname,did) 
values('Staff6',6);
INSERT INTO Staff(sname,did) 
values('Staff7',7);
INSERT INTO Staff(sname,did) 
values('Staff8',8);
INSERT INTO Staff(sname,did) 
values('Staff9',9);
INSERT INTO Staff(sname,did) 
values('Staff10',10);

INSERT INTO faculty(fname,did) 
values('Faculty1',1);
INSERT INTO faculty(fname,did) 
values('Faculty2',2);
INSERT INTO faculty(fname,did) 
values('Faculty3',3);
INSERT INTO faculty(fname,did) 
values('Faculty4',4);
INSERT INTO faculty(fname,did) 
values('Faculty5',5);
INSERT INTO faculty(fname,did) 
values('Faculty6',6);
INSERT INTO faculty(fname,did) 
values('Faculty7',7);
INSERT INTO faculty(fname,did) 
values('Faculty8',8);
INSERT INTO faculty(fname,did) 
values('Faculty9',9);
INSERT INTO faculty(fname,did) 
values('Faculty10',10);

INSERT INTO courses(cname,meets_at,room,f_id,course_limit) 
values('Course1_Name','Cours1_Meets_at','Course1_Room',1,20);
INSERT INTO courses(cname,meets_at,room,f_id,course_limit) 
values('Course2_Name','Course2_Meets_at','Course2_Room',2,20);
INSERT INTO courses(cname,meets_at,room,f_id,course_limit) 
values('Course3_Name','Course3_Meets_at','Course3_Room',3,20);
INSERT INTO courses(cname,meets_at,room,f_id,course_limit) 
values('Course4_Name','Course4_Meets_at','Course4_Room',4,20);
INSERT INTO courses(cname,meets_at,room,f_id,course_limit) 
values('Course5_Name','Course5_Meets_at','Course5_Room',5,20);
INSERT INTO courses(cname,meets_at,room,f_id,course_limit) 
values('Course6_Name','Course6_Meets_at','Course6_Room',6,20);
INSERT INTO courses(cname,meets_at,room,f_id,course_limit) 
values('Course7_Name','Course7_Meets_at','Course7_Room',7,20);
INSERT INTO courses(cname,meets_at,room,f_id,course_limit) 
values('Course8_Name','Course8_Meets_at','Course8_Room',8,20);
INSERT INTO courses(cname,meets_at,room,f_id,course_limit) 
values('Course9_Name','Course9_Meets_at','Course9_Room',9,20);
INSERT INTO courses(cname,meets_at,room,f_id,course_limit) 
values('Course10_Name','Course10_Meets_at','Course10_Room',10,20);


INSERT INTO enrolled(sid,cid,exam1,exam2,final) 
values(1,1,80,90,95);
INSERT INTO enrolled(sid,cid,exam1,exam2,final) 
values(2,2,80,90,95);
INSERT INTO enrolled(sid,cid,exam1,exam2,final) 
values(3,3,80,90,95);
INSERT INTO enrolled(sid,cid,exam1,exam2,final) 
values(4,4,80,90,95);
INSERT INTO enrolled(sid,cid,exam1,exam2,final) 
values(5,5,80,90,95);
INSERT INTO enrolled(sid,cid,exam1,exam2,final) 
values(6,6,80,90,95);
INSERT INTO enrolled(sid,cid,exam1,exam2,final) 
values(7,7,80,90,95);
INSERT INTO enrolled(sid,cid,exam1,exam2,final) 
values(8,8,80,90,95);
INSERT INTO enrolled(sid,cid,exam1,exam2,final) 
values(9,9,80,90,95);
INSERT INTO enrolled(sid,cid,exam1,exam2,final) 
values(10,10,80,90,95);










drop table  student;
drop table department;
drop table staff;
drop table faculty;
drop table courses;
drop table course_count;
drop table enrolled;

CREATE TABLE student (
     sid    varchar(40) PRIMARY KEY ,
     sname   varchar(40),
     major   varchar(40),
     s_level   varchar(40),
     age integer
);


CREATE TABLE department (
     did    varchar(40) PRIMARY KEY ,
     dname   varchar(40)
);

CREATE TABLE staff (
     sid    varchar(40) PRIMARY KEY ,
     sname   varchar(40),
     did   varchar(40) references department(did)
);

CREATE TABLE faculty (
     fid    varchar(40) PRIMARY KEY ,
     fname   varchar(40),
     did   varchar(40) references department(did)
);

CREATE TABLE courses (
     cid    varchar(40) PRIMARY KEY ,
     cname   varchar(40),
     meets_at   varchar(40),
     room   varchar(40),
     f_id   varchar(40) references faculty(fid),
     course_limit integer
);

CREATE TABLE enrolled (
     sid    varchar(40) ,
     cid    varchar(40),
     exam1   integer,
     exam2   integer,
     final integer,
     PRIMARY KEY(sid, cid)
);

CREATE TABLE course_count ( 
     cid    varchar(40) references courses(cid),
     course_limit integer,
     enrolled_count integer
);

INSERT INTO student(sname,major,s_level,age) 
values('Student1_Name','Student1_Major','Student1_Level',25);
INSERT INTO student(sname,major,s_level,age) 
values('Student2_Name','Student2_Major','Student2_Level',26);
INSERT INTO student(sname,major,s_level,age) 
values('Student3_Name','Student3_Major','Student3_Level',27);
INSERT INTO student(sname,major,s_level,age) 
values('Student4_Name','Student4_Major','Student4_Level',28);
INSERT INTO student(sname,major,s_level,age) 
values('Student5_Name','Student5_Major','Student5_Level',29);
INSERT INTO student(sname,major,s_level,age) 
values('Student6_Name','Student6_Major','Student6_Level',21);
INSERT INTO student(sname,major,s_level,age) 
values('Student7_Name','Student7_Major','Student7_Level',22);
INSERT INTO student(sname,major,s_level,age) 
values('Student8_Name','Student8_Major','Student8_Level',23);
INSERT INTO student(sname,major,s_level,age) 
values('Student9_Name','Student9_Major','Student9_Level',24);
INSERT INTO student(sname,major,s_level,age) 
values('Student10_Name','Student10_Major','Student10_Level',20);

INSERT INTO department(dname) 
values('Department1');
INSERT INTO department(dname) 
values('Department2');
INSERT INTO department(dname) 
values('Department3');
INSERT INTO department(dname) 
values('Department4');
INSERT INTO department(dname) 
values('Department5');
INSERT INTO department(dname) 
values('Department6');
INSERT INTO department(dname) 
values('Department7');
INSERT INTO department(dname) 
values('Department8');
INSERT INTO department(dname) 
values('Department9');
INSERT INTO department(dname) 
values('Department10');


INSERT INTO Staff(sname,did) 
values('Staff',1);
INSERT INTO Staff(sname,did) 
values('Staff2',2);
INSERT INTO Staff(sname,did) 
values('Staff3',3);
INSERT INTO Staff(sname,did) 
values('Staff4',4);
INSERT INTO Staff(sname,did) 
values('Staff5',5);
INSERT INTO Staff(sname,did) 
values('Staff6',6);
INSERT INTO Staff(sname,did) 
values('Staff7',7);
INSERT INTO Staff(sname,did) 
values('Staff8',8);
INSERT INTO Staff(sname,did) 
values('Staff9',9);
INSERT INTO Staff(sname,did) 
values('Staff10',10);

INSERT INTO faculty(fname,did) 
values('Faculty1',1);
INSERT INTO faculty(fname,did) 
values('Faculty2',2);
INSERT INTO faculty(fname,did) 
values('Faculty3',3);
INSERT INTO faculty(fname,did) 
values('Faculty4',4);
INSERT INTO faculty(fname,did) 
values('Faculty5',5);
INSERT INTO faculty(fname,did) 
values('Faculty6',6);
INSERT INTO faculty(fname,did) 
values('Faculty7',7);
INSERT INTO faculty(fname,did) 
values('Faculty8',8);
INSERT INTO faculty(fname,did) 
values('Faculty9',9);
INSERT INTO faculty(fname,did) 
values('Faculty10',10);

INSERT INTO courses(cname,meets_at,room,f_id,course_limit) 
values('Course1_Name','Cours1_Meets_at','Course1_Room',1,20);
INSERT INTO courses(cname,meets_at,room,f_id,course_limit) 
values('Course2_Name','Course2_Meets_at','Course2_Room',2,20);
INSERT INTO courses(cname,meets_at,room,f_id,course_limit) 
values('Course3_Name','Course3_Meets_at','Course3_Room',3,20);
INSERT INTO courses(cname,meets_at,room,f_id,course_limit) 
values('Course4_Name','Course4_Meets_at','Course4_Room',4,20);
INSERT INTO courses(cname,meets_at,room,f_id,course_limit) 
values('Course5_Name','Course5_Meets_at','Course5_Room',5,20);
INSERT INTO courses(cname,meets_at,room,f_id,course_limit) 
values('Course6_Name','Course6_Meets_at','Course6_Room',6,20);
INSERT INTO courses(cname,meets_at,room,f_id,course_limit) 
values('Course7_Name','Course7_Meets_at','Course7_Room',7,20);
INSERT INTO courses(cname,meets_at,room,f_id,course_limit) 
values('Course8_Name','Course8_Meets_at','Course8_Room',8,20);
INSERT INTO courses(cname,meets_at,room,f_id,course_limit) 
values('Course9_Name','Course9_Meets_at','Course9_Room',9,20);
INSERT INTO courses(cname,meets_at,room,f_id,course_limit) 
values('Course10_Name','Course10_Meets_at','Course10_Room',10,20);


INSERT INTO enrolled(sid,cid,exam1,exam2,final) 
values(1,1,80,90,95);
INSERT INTO enrolled(sid,cid,exam1,exam2,final) 
values(2,2,80,90,95);
INSERT INTO enrolled(sid,cid,exam1,exam2,final) 
values(3,3,80,90,95);
INSERT INTO enrolled(sid,cid,exam1,exam2,final) 
values(4,4,80,90,95);
INSERT INTO enrolled(sid,cid,exam1,exam2,final) 
values(5,5,80,90,95);
INSERT INTO enrolled(sid,cid,exam1,exam2,final) 
values(6,6,80,90,95);
INSERT INTO enrolled(sid,cid,exam1,exam2,final) 
values(7,7,80,90,95);
INSERT INTO enrolled(sid,cid,exam1,exam2,final) 
values(8,8,80,90,95);
INSERT INTO enrolled(sid,cid,exam1,exam2,final) 
values(9,9,80,90,95);
INSERT INTO enrolled(sid,cid,exam1,exam2,final) 
values(10,10,80,90,95);