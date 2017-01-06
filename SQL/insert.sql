/* 
 * File insert.sql
 * Inserts some dummy data into the Makerspace Project Manager database, to allow for testing.
 * Group members: 
 *    Remo Scolati:   remo_scolati@hotmail.com
 *    Mikel Grabocka: mgrabocka@unibz.it
 *    Matteo Nardini: mnardini@unibz.it
 */

-- Data for table PROJECT 

INSERT INTO PROJECT (project_id, title, description, status, seeking_collaboration) 
VALUES (100,'Board Game','Creating a board game for the management course','active',FALSE);

INSERT INTO PROJECT (project_id, title, description, status, seeking_collaboration) 
VALUES (101,'Arduino Workshop','A one week workshop on what to use Arduino for','completed',FALSE);

INSERT INTO PROJECT (project_id, title, description, status, seeking_collaboration) 
VALUES (102,'Dependecies free','A project without bookings, purchases and such. For test purposes','completed',FALSE);

-- Data for table GENERAL_ROLE

INSERT INTO GENERAL_ROLE (role_id, name, description) 
VALUES (1020,'Staff','Technical support');

INSERT INTO GENERAL_ROLE (role_id, name, description)
VALUES (1021,'Teacher','Academic staff of the University of Bolzano');

INSERT INTO GENERAL_ROLE (role_id, name, description)
VALUES (1022,'Student','Student of the University of Bolzano');

INSERT INTO GENERAL_ROLE (role_id, name, description)
VALUES (1023,'Dependecies Free','A role that none has. For test purposes');

-- Data for table MAKERSPACE_USER

INSERT INTO MAKERSPACE_USER (user_id, name, user_role, email)
VALUES (10,'Remo Scolati',1020,'rscolati@unibz.it');

INSERT INTO MAKERSPACE_USER (user_id, name, user_role, email)
VALUES (11,'Mikel Grabocka',1022,'mgrabocka@unibz.it');

INSERT INTO MAKERSPACE_USER (user_id, name, user_role, email)
VALUES (12,'Matteo Nardini',1021,'mnardini@unibz.it');

INSERT INTO MAKERSPACE_USER (user_id, name, user_role, email)
VALUES (13,'Dependecies Free',1021,'dep@free.it');

-- Data for table MATERIAL

INSERT INTO MATERIAL (material_id, name, description, unitary_price, units_of_measure, units_available)
VALUES (200,'Paper','',0.02,'Sheet',1000);

INSERT INTO MATERIAL (material_id, name, description, unitary_price, units_of_measure, units_available)
VALUES (201,'Red Raw Plastic','Input for 3D printer',3,'m',2000);

INSERT INTO MATERIAL (material_id, name, description, unitary_price, units_of_measure, units_available)
VALUES (203,'Blue Raw Plastic','Input for 3D printer',3,'m',1650);

INSERT INTO MATERIAL (material_id, name, description, unitary_price, units_of_measure, units_available)
VALUES (205,'Green Raw Plastic','Input for 3D printer',3,'m',905);

-- Data for table FREE_INF

INSERT INTO FREE_INF (free_inf_id, available, name)
VALUES (301,TRUE,'Room 301');

INSERT INTO FREE_INF (free_inf_id, available, name)
VALUES (305,TRUE,'Room 305');

INSERT INTO FREE_INF (free_inf_id, available, name)
VALUES (321,TRUE,'Room 321');

INSERT INTO FREE_INF (free_inf_id, available, name)
VALUES (322,TRUE,'Room 322');

INSERT INTO FREE_INF (free_inf_id, available, name)
VALUES (323,TRUE,'Room 323 - Not booked');

-- Data for table TECHNICAL_INF

INSERT INTO TECHNICAL_INF (technical_inf_id, available, name)
VALUES (501,TRUE,'3D printer 101');

INSERT INTO TECHNICAL_INF (technical_inf_id, available, name)
VALUES (502,TRUE,'3D printer 102');

INSERT INTO TECHNICAL_INF (technical_inf_id, available, name)
VALUES (503,TRUE,'3D printer 103');

-- Data for table REQUEST

INSERT INTO REQUEST (request_id, title, project_id, technical_inf_id, handled_by_user)
VALUES (401,'Parts to print',100,501,10);

INSERT INTO REQUEST (request_id, title, project_id, technical_inf_id, handled_by_user)
VALUES (402,'Something to print',100,502,11);

INSERT INTO REQUEST (request_id, title, project_id, technical_inf_id, handled_by_user)
VALUES (403,'Something else to print',101,503,10);

-- Data for table MESSAGE

INSERT INTO MESSAGE (message_id, message_text, request_id, message_time, message_author)
VALUES (601,'Need to print some things',401,'2016-1-16 15:30:26',12);

INSERT INTO MESSAGE (message_id, message_text, request_id, message_time, message_author)
VALUES (602,'Need to print some more things',402,'2016-1-16 16:45:16',12);

INSERT INTO MESSAGE (message_id, message_text, request_id, message_time, message_author)
VALUES (603,'Need to print even more things',403,'2016-1-17 10:35:55',10);

-- Data for table PARTICIPATION

INSERT INTO PARTICIPATION (project_id, user_id, project_role)
VALUES (101,10,'administrator');

INSERT INTO PARTICIPATION (project_id, user_id, project_role)
VALUES (101,11,'collaborator');

INSERT INTO PARTICIPATION (project_id, user_id, project_role)
VALUES (101,12,'collaborator');

INSERT INTO PARTICIPATION (project_id, user_id, project_role)
VALUES (100,10,'collaborator');

INSERT INTO PARTICIPATION (project_id, user_id, project_role)
VALUES (100,11,'collaborator');

INSERT INTO PARTICIPATION (project_id, user_id, project_role)
VALUES (100,12,'administrator');

-- Data for table PURCHASE

INSERT INTO PURCHASE (purchase_id, project_id, material_id, units, total_price)
VALUES (801,100,200,5,0.10);

INSERT INTO PURCHASE (purchase_id, project_id, material_id, units, total_price)
VALUES (802,100,201,10,30);

INSERT INTO PURCHASE (purchase_id, project_id, material_id, units, total_price)
VALUES (803,101,201,6,18);

INSERT INTO PURCHASE (purchase_id, project_id, material_id, units, total_price)
VALUES (804,101,203,10,30);

-- Data for table BOOKING

INSERT INTO BOOKING (booking_id, project_id, free_inf_id, start_time, end_time)
VALUES (901,100,301,'2016-1-16 15:00:00','2016-1-16 17:00:00');

INSERT INTO BOOKING (booking_id, project_id, free_inf_id, start_time, end_time)
VALUES (902,100,305,'2016-1-18 08:00:00','2016-1-18 17:00:00');

INSERT INTO BOOKING (booking_id, project_id, free_inf_id, start_time, end_time)
VALUES (903,101,321,'2016-2-17 08:00:00','2016-2-18 17:00:00');

INSERT INTO BOOKING (booking_id, project_id, free_inf_id, start_time, end_time)
VALUES (904,101,322,'2016-3-19 08:30:00','2016-3-19 13:30:00');