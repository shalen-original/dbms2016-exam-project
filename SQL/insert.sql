/* 
 * File insert.sql
 * Inserts some dummy data into the Makerspace Project Manager database, to allow for easy testing.
 * Group members: 
 *    Remo Scolati:   remo_scolati@hotmail.com
 *    Mikel Grabocka: mgrabocka@unibz.it
 *    Matteo Nardini: mnardini@unibz.it
 */
 
INSERT INTO PROJECT VALUES (100,'Board Game','Creating a board game for the managment course','active',FALSE);
INSERT INTO GENERAL_ROLE VALUES (1000,'Staff','Technical support');
INSERT INTO GENERAL_ROLE VALUES (1001,'Teacher','Theoretical support');
INSERT INTO GENERAL_ROLE VALUES (1002,'Student','Output of the project');
INSERT INTO MAKERSPACE_USER VALUES (10,'Remo Scolati',1000,'rscolati@unibz.it');
INSERT INTO MAKERSPACE_USER VALUES (11,'Mikel Grabocka',1002,'mgrabocka@unibz.it');
INSERT INTO MAKERSPACE_USER VALUES (12,'Matteo Nardini',1001,'mnardini@unibz.it');
INSERT INTO MATERIAL VALUES (200,'Paper','',2,'Sheet',1000);
INSERT INTO MATERIAL VALUES (201,'Red Raw Plastic','Input for 3D printer',3,'m',2000);
INSERT INTO FREE_INF VALUES (301,TRUE,'Room 301');
INSERT INTO FREE_INF VALUES (305,TRUE,'Room 305');
INSERT INTO TECHNICAL_INF VALUES (501,TRUE,'3D printer 101');
INSERT INTO TECHNICAL_INF VALUES (502,TRUE,'3D printer 102');
INSERT INTO REQUEST VALUES (401,'Something to print',100,501,10);
INSERT INTO REQUEST VALUES (402,'Something to print',100,502,11);
INSERT INTO MESSAGE VALUES (601,'Need to print some more things',401,'2016-1-16 15:30:26');
INSERT INTO MESSAGE VALUES (602,'Need to print some more things',402,'2016-1-16 16:45:16');
INSERT INTO PARTICIPATION VALUES (100,10,'collaborator');
INSERT INTO PARTICIPATION VALUES (100,11,'collaborator');
INSERT INTO PARTICIPATION VALUES (100,12,'responsible');
INSERT INTO PURCHASE VALUES (801,100,200,5);
INSERT INTO PURCHASE VALUES (802,100,201,10);
INSERT INTO BOOKING VALUES (901,100,301,'2016-1-15','2016-1-16');
INSERT INTO BOOKING VALUES (902,100,305,'2016-1-18','2016-1-20');


INSERT INTO PROJECT VALUES (101,'Arduino Workshop','An one week workshop on what to use Arduino for','completed',FALSE);
INSERT INTO GENERAL_ROLE VALUES (1020,'Staff','Technical support');
INSERT INTO GENERAL_ROLE VALUES (1021,'Teacher','Theoretical support');
INSERT INTO GENERAL_ROLE VALUES (1022,'Student','Output of the project');
INSERT INTO MAKERSPACE_USER VALUES (20,'Remo Scolati',1022,'rscolati@unibz.it');
INSERT INTO MAKERSPACE_USER VALUES (21,'Mikel Grabocka',1021,'mgrabocka@unibz.it');
INSERT INTO MAKERSPACE_USER VALUES (22,'Matteo Nardini',1020,'mnardini@unibz.it');
INSERT INTO MATERIAL VALUES (202,'Thick Paper','',2,'Sheet',1200);
INSERT INTO MATERIAL VALUES (203,'Blue Raw Plastic','Input for 3D printer',3,'m',1650);
INSERT INTO FREE_INF VALUES (321,TRUE,'Room 321');
INSERT INTO FREE_INF VALUES (322,TRUE,'Room 322');
INSERT INTO TECHNICAL_INF VALUES (503,TRUE,'3D printer 103');
INSERT INTO TECHNICAL_INF VALUES (504,TRUE,'3D printer 104');
INSERT INTO REQUEST VALUES (403,'Something to print',101,503,21);
INSERT INTO REQUEST VALUES (404,'Something to print',101,504,22);
INSERT INTO MESSAGE VALUES (603,'Need to print some more things',403,'2016-1-12 10:35:55');
INSERT INTO MESSAGE VALUES (604,'Need to print some more things',404,'2016-1-13 11:26:20');
INSERT INTO PARTICIPATION VALUES (101,20,'responsible');
INSERT INTO PARTICIPATION VALUES (101,21,'collaborator');
INSERT INTO PARTICIPATION VALUES (101,22,'collaborator');
INSERT INTO PURCHASE VALUES (803,101,202,5);
INSERT INTO PURCHASE VALUES (804,101,203,10);
INSERT INTO BOOKING VALUES (903,101,321,'2016-2-17','2016-2-18');
INSERT INTO BOOKING VALUES (904,101,322,'2016-3-18','2016-3-19');
