insert into public.user (EMAIL, FAMILY, NAME) values ( 'amir.azimi.alasti@gmail.com', 'Azimi', 'Amir');
insert into public.user (EMAIL, FAMILY, NAME) values ( 'martin.belak@gmail.com', 'Belak', 'Martin');
insert into public.user (EMAIL, FAMILY, NAME) values ( 'user@gmail.com', 'A', 'B');
insert into public.INITIAL_CREDIT_LIMIT (AMOUNT, USER_ID) values (200, select id from public.user where email ='amir.azimi.alasti@gmail.com');
insert into public.INITIAL_CREDIT_LIMIT (AMOUNT, USER_ID) values (500, select id from public.user where email ='martin.belak@gmail.com');
insert into public.INITIAL_CREDIT_LIMIT (AMOUNT, USER_ID) values (300, select id from public.user where email ='user@gmail.com');
insert into PUBLIC.RESERVATION (BOOKING_DATE, COST, SUCCESSFUL, TRANSACTION_NUMBER, USER_ID) VALUES ( '2021-04-17 00:00:00', 301, false, 'TR0001', select id from public.user where email ='user@gmail.com' );