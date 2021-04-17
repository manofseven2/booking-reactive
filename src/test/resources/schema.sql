
DROP TABLE  IF EXISTS RESERVATION;
DROP TABLE  IF EXISTS INITIAL_CREDIT_LIMIT;
DROP TABLE  IF EXISTS USER;
create table USER
(
	ID BIGINT  auto_increment
		primary key,
	EMAIL VARCHAR(150)
		unique,
	FAMILY VARCHAR(150),
	NAME VARCHAR(100)
);
create table INITIAL_CREDIT_LIMIT
(
	ID BIGINT  auto_increment
		primary key,
	AMOUNT BIGINT,
	USER_ID BIGINT,
	constraint FKSHLOQSWFF6KLQ66IP6SDQACRV
		foreign key (USER_ID) references USER
);
create table RESERVATION
(
	ID BIGINT auto_increment
		primary key,
	BOOKING_DATE TIMESTAMP,
	COST BIGINT,
	SUCCESSFUL BOOLEAN,
	TRANSACTION_NUMBER VARCHAR(100),
	USER_ID BIGINT,
	constraint FKM4OIMK0L1757O9PWAVORJ6LJG
		foreign key (USER_ID) references USER
);

insert into public.user (EMAIL, FAMILY, NAME) values ( 'amir.azimi.alasti@gmail.com', 'Azimi', 'Amir');
insert into public.user (EMAIL, FAMILY, NAME) values ( 'martin.belak@gmail.com', 'Belak', 'Martin');
insert into public.user (EMAIL, FAMILY, NAME) values ( 'user@gmail.com', 'A', 'B');
insert into public.INITIAL_CREDIT_LIMIT (AMOUNT, USER_ID) values (200, select id from public.user where email ='amir.azimi.alasti@gmail.com');
insert into public.INITIAL_CREDIT_LIMIT (AMOUNT, USER_ID) values (500, select id from public.user where email ='martin.belak@gmail.com');
insert into public.INITIAL_CREDIT_LIMIT (AMOUNT, USER_ID) values (300, select id from public.user where email ='user@gmail.com');
insert into PUBLIC.RESERVATION (BOOKING_DATE, COST, SUCCESSFUL, TRANSACTION_NUMBER, USER_ID) VALUES ( '2021-04-17 00:00:00', 301, false, 'TR0001', select id from public.user where email ='user@gmail.com' );