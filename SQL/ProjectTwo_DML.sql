--initial inserts for lookup tables
INSERT INTO SU_ROLES VALUES(100, 'Employee');
INSERT INTO SU_ROLES VALUES(200, 'Manager');
/
INSERT INTO SB_LANES VALUES(10, 'Backlog');
INSERT INTO SB_LANES VALUES(20, 'TODO');
INSERT INTO SB_LANES VALUES(30, 'In Progress');
INSERT INTO SB_LANES VALUES(40, 'Test');
INSERT INTO SB_LANES VALUES(50, 'Verify');
INSERT INTO SB_LANES VALUES(60, 'Done');
/
--Test Entries--
INSERT INTO SCRUM_USERS VALUES (1,'Patrick', 'Onion', 'pusername', 'password', 200, 'jpwrunyan@hotmail.com');
INSERT INTO SCRUM_USERS VALUES (2,'Mister','Rogers','musername','password',200,'brogers0101@gmail.com');
INSERT INTO SCRUM_USERS VALUES (3,'Burly','Gerbil','busername','password',200,'jibbgh@gmail.com');
INSERT INTO SCRUM_USERS VALUES (4,'Elvis','Yangster','eusername','password',200,'waveeyang@gmail.com');
INSERT INTO SCRUM_USERS VALUES (5,'Demo','User','u','p',200,'waveeyang@gmail.com');
INSERT INTO SCRUM_USERS VALUES (6,'Inebriated','Cephalopod','i','c',100,'waveeyang@gmail.com');
INSERT INTO SCRUM_USERS VALUES (7,'Slurred','Squid','s','s',100,'waveeyang@gmail.com');
/
--
INSERT INTO SCRUM_BOARDS(USER_ID,SB_NAME) VALUES (1,'Patrick Board'); 
INSERT INTO SCRUM_BOARDS(USER_ID,SB_NAME) VALUES (2,'Ben Board'); 
INSERT INTO SCRUM_BOARDS(USER_ID,SB_NAME) VALUES (3,'Jibril Board'); 
INSERT INTO SCRUM_BOARDS(USER_ID,SB_NAME) VALUES (4,'Elvis Board'); 

INSERT INTO SCRUM_BOARDS(USER_ID,SB_NAME) VALUES (4,'Elvis Board Numba 2'); 
INSERT INTO SCRUM_BOARDS(USER_ID,SB_NAME) VALUES (4,'Elvis Board the Third'); 
INSERT INTO SCRUM_BOARDS(USER_ID,SB_NAME) VALUES (4,'Elvis Board Quatro'); 
INSERT INTO SCRUM_BOARDS(USER_ID,SB_NAME) VALUES (1,'BEJP - Project 2'); 
/
--user board join table
INSERT INTO USERS_BOARDS (USER_ID, SB_ID) VALUES(
 (SELECT USER_ID FROM SCRUM_USERS WHERE USER_USERNAME = 'pusername'),
 (SELECT SB_ID FROM SCRUM_BOARDS WHERE SB_NAME = 'Patrick Board')
);
INSERT INTO USERS_BOARDS (USER_ID, SB_ID) VALUES(
 (SELECT USER_ID FROM SCRUM_USERS WHERE USER_USERNAME = 'musername'),
 (SELECT SB_ID FROM SCRUM_BOARDS WHERE SB_NAME = 'Ben Board')
);
INSERT INTO USERS_BOARDS (USER_ID, SB_ID) VALUES(
 (SELECT USER_ID FROM SCRUM_USERS WHERE USER_USERNAME = 'busername'),
 (SELECT SB_ID FROM SCRUM_BOARDS WHERE SB_NAME = 'Jibril Board')
);
INSERT INTO USERS_BOARDS (USER_ID, SB_ID) VALUES(
 (SELECT USER_ID FROM SCRUM_USERS WHERE USER_USERNAME = 'eusername'),
 (SELECT SB_ID FROM SCRUM_BOARDS WHERE SB_NAME = 'Elvis Board')
);
INSERT INTO USERS_BOARDS (USER_ID, SB_ID) VALUES(
 (SELECT USER_ID FROM SCRUM_USERS WHERE USER_USERNAME = 'eusername'),
 (SELECT SB_ID FROM SCRUM_BOARDS WHERE SB_NAME = 'Elvis Board Numba 2')
);
INSERT INTO USERS_BOARDS (USER_ID, SB_ID) VALUES(
 (SELECT USER_ID FROM SCRUM_USERS WHERE USER_USERNAME = 'eusername'),
 (SELECT SB_ID FROM SCRUM_BOARDS WHERE SB_NAME = 'Elvis Board the Third')
);
INSERT INTO USERS_BOARDS (USER_ID, SB_ID) VALUES(
 (SELECT USER_ID FROM SCRUM_USERS WHERE USER_USERNAME = 'eusername'),
 (SELECT SB_ID FROM SCRUM_BOARDS WHERE SB_NAME = 'Elvis Board Quatro')
);
INSERT INTO USERS_BOARDS (USER_ID, SB_ID) VALUES(
 (SELECT USER_ID FROM SCRUM_USERS WHERE USER_USERNAME = 'pusername'),
 (SELECT SB_ID FROM SCRUM_BOARDS WHERE SB_NAME = 'BEJP - Project 2')
);
/
INSERT INTO SB_STORIES(SB_ID,SBS_DESCRIPTION,SBS_POINTS,SBL_ID,SBS_DONE) VALUES((SELECT SB_ID FROM SCRUM_BOARDS WHERE SB_NAME = 'BEJP - Project 2')
,'10 - As any user I can log in.',10,60,TO_TIMESTAMP('2017-10-25 06:14:00.742000000', 'YYYY-MM-DD HH24:MI:SS.FF'));
INSERT INTO SB_STORIES(SB_ID,SBS_DESCRIPTION,SBS_POINTS,SBL_ID,SBS_DONE) VALUES((SELECT SB_ID FROM SCRUM_BOARDS WHERE SB_NAME = 'BEJP - Project 2')
,'5 - As a SL I can create a new board.',5,60,TO_TIMESTAMP('2017-10-27 06:14:00.742000000', 'YYYY-MM-DD HH24:MI:SS.FF'));
INSERT INTO SB_STORIES(SB_ID,SBS_DESCRIPTION,SBS_POINTS,SBL_ID,SBS_DONE) VALUES((SELECT SB_ID FROM SCRUM_BOARDS WHERE SB_NAME = 'BEJP - Project 2')
,'5 - As any user I can view a list of all my boards.',5,60,TO_TIMESTAMP('2017-10-28 06:14:00.742000000', 'YYYY-MM-DD HH24:MI:SS.FF'));
INSERT INTO SB_STORIES(SB_ID,SBS_DESCRIPTION,SBS_POINTS,SBL_ID,SBS_DONE) VALUES((SELECT SB_ID FROM SCRUM_BOARDS WHERE SB_NAME = 'BEJP - Project 2')
,'1 - As any user I can logout.',1,60,TO_TIMESTAMP('2017-10-28 06:14:00.742000000', 'YYYY-MM-DD HH24:MI:SS.FF'));
INSERT INTO SB_STORIES(SB_ID,SBS_DESCRIPTION,SBS_POINTS,SBL_ID,SBS_DONE) VALUES((SELECT SB_ID FROM SCRUM_BOARDS WHERE SB_NAME = 'BEJP - Project 2')
,'5 - As a SL I can edit a board.',5,60,TO_TIMESTAMP('2017-10-28 06:14:00.742000000', 'YYYY-MM-DD HH24:MI:SS.FF'));
INSERT INTO SB_STORIES(SB_ID,SBS_DESCRIPTION,SBS_POINTS,SBL_ID,SBS_DONE) VALUES((SELECT SB_ID FROM SCRUM_BOARDS WHERE SB_NAME = 'BEJP - Project 2')
,'5 - As any user I can view a single board in detail.',5,60,TO_TIMESTAMP('2017-10-30 06:14:00.742000000', 'YYYY-MM-DD HH24:MI:SS.FF'));
INSERT INTO SB_STORIES(SB_ID,SBS_DESCRIPTION,SBS_POINTS,SBL_ID,SBS_DONE) VALUES((SELECT SB_ID FROM SCRUM_BOARDS WHERE SB_NAME = 'BEJP - Project 2')
,'5 - As any user I can reposition a story to a new lane.',5,60,TO_TIMESTAMP('2017-11-02 06:14:00.742000000', 'YYYY-MM-DD HH24:MI:SS.FF'));
INSERT INTO SB_STORIES(SB_ID,SBS_DESCRIPTION,SBS_POINTS,SBL_ID,SBS_DONE) VALUES((SELECT SB_ID FROM SCRUM_BOARDS WHERE SB_NAME = 'BEJP - Project 2')
,'5 - As any user I can check off a task.',5,60,TO_TIMESTAMP('2017-11-02 06:14:00.742000000', 'YYYY-MM-DD HH24:MI:SS.FF'));
INSERT INTO SB_STORIES(SB_ID,SBS_DESCRIPTION,SBS_POINTS,SBL_ID,SBS_DONE) VALUES((SELECT SB_ID FROM SCRUM_BOARDS WHERE SB_NAME = 'BEJP - Project 2')
,'5 - As any user I can view a burndown chart for a board.',5,60,TO_TIMESTAMP('2017-11-04 06:14:00.742000000', 'YYYY-MM-DD HH24:MI:SS.FF'));
INSERT INTO SB_STORIES(SB_ID,SBS_DESCRIPTION,SBS_POINTS,SBL_ID,SBS_DONE) VALUES((SELECT SB_ID FROM SCRUM_BOARDS WHERE SB_NAME = 'BEJP - Project 2')
,'5 - As a SL I can edit a story.',5,60,TO_TIMESTAMP('2017-11-04 06:14:00.742000000', 'YYYY-MM-DD HH24:MI:SS.FF'));
INSERT INTO SB_STORIES(SB_ID,SBS_DESCRIPTION,SBS_POINTS,SBL_ID,SBS_DONE) VALUES((SELECT SB_ID FROM SCRUM_BOARDS WHERE SB_NAME = 'BEJP - Project 2')
,'5 - As a SL I can add another user to a board.',5,60,TO_TIMESTAMP('2017-11-04 06:14:00.742000000', 'YYYY-MM-DD HH24:MI:SS.FF'));
INSERT INTO SB_STORIES(SB_ID,SBS_DESCRIPTION,SBS_POINTS,SBL_ID,SBS_DONE) VALUES((SELECT SB_ID FROM SCRUM_BOARDS WHERE SB_NAME = 'BEJP - Project 2')
,'5 - As a SL I can add tasks to a story.',5,60,TO_TIMESTAMP('2017-11-06 06:14:00.742000000', 'YYYY-MM-DD HH24:MI:SS.FF'));
INSERT INTO SB_STORIES(SB_ID,SBS_DESCRIPTION,SBS_POINTS,SBL_ID,SBS_DONE) VALUES((SELECT SB_ID FROM SCRUM_BOARDS WHERE SB_NAME = 'BEJP - Project 2')
,'5 - As a SL I can add a story to a lane.',5,60,TO_TIMESTAMP('2017-11-06 06:14:00.742000000', 'YYYY-MM-DD HH24:MI:SS.FF'));
/
--UPDATE SB_STORIES SET SBS_DONE = (TIMESTAMP_VALUE) VALUES (TO_TIMESTAMP('2014-07-02 06:14:00.742000000', 'YYYY-MM-DD HH24:MI:SS.FF'));
/
--INSERT INTO SB_TASKS(SBT_ID,SBS_ID,SBT_STATUS,SBT_DESCRIPTION) VALUES(1,1,0,'Do it. Do it now.');
/
COMMIT;
/