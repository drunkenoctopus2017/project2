--SQL DDL for Project Two

--lookup table for users, 1-employee, 2-manager
--ORDER IS IMPORTANT HERE!
DROP TABLE SB_TASKS;
DROP TABLE SB_STORIES;
DROP TABLE SB_LANES;
DROP TABLE USERS_BOARDS;
DROP TABLE SCRUM_BOARDS;
DROP TABLE SCRUM_USERS;
DROP TABLE SU_ROLES;
/
CREATE TABLE SU_ROLES(
  ROLE_ID INT,
  ROLE_NAME VARCHAR2(4000) UNIQUE,
  PRIMARY KEY(ROLE_ID)
);
/
CREATE TABLE SCRUM_USERS(
  USER_ID INT,
  USER_FN VARCHAR2(4000),
  USER_LN VARCHAR2(4000),
  USER_USERNAME VARCHAR2(4000) UNIQUE NOT NULL,
  USER_PASSWORD VARCHAR2(4000) NOT NULL,
  ROLE_ID INT DEFAULT 0 NOT NULL,
  USER_EMAIL VARCHAR2(4000),
  PRIMARY KEY(USER_ID),
  FOREIGN KEY(ROLE_ID) REFERENCES SU_ROLES(ROLE_ID)
);
/
CREATE TABLE SCRUM_BOARDS(
  SB_ID INT,
  USER_ID INT NOT NULL,
  SB_NAME VARCHAR2(4000) NOT NULL,
  SB_DURATION INT DEFAULT 14 NOT NULL, --for length of sprint: Patrick changed name. Length isn't as clear as duration.
  SB_START TIMESTAMP DEFAULT CURRENT_TIMESTAMP, --for start date of sprint
  PRIMARY KEY(SB_ID),
  FOREIGN KEY(USER_ID) REFERENCES SCRUM_USERS(USER_ID)
);
/
--Join table? users and boards is a many-to-many relationship
CREATE TABLE USERS_BOARDS(
  USER_ID INT,
  SB_ID INT,
  PRIMARY KEY(USER_ID,SB_ID),
  FOREIGN KEY (USER_ID) REFERENCES SCRUM_USERS(USER_ID),
  FOREIGN KEY (SB_ID) REFERENCES SCRUM_BOARDS(SB_ID)
);
/
--lookup table for stories? 1-backlog, 2-TODO, 3-In Progress, 4-Test, 5-Verify, 6-Done (Tentative)
CREATE TABLE SB_LANES(
  SBL_ID INT,
  SBL_NAME VARCHAR2(4000) UNIQUE,
  PRIMARY KEY(SBL_ID)
);
/
CREATE TABLE SB_STORIES(
  SBS_ID INT,
  SB_ID INT NOT NULL,
  SBS_DESCRIPTION VARCHAR2(4000),
  SBS_POINTS INT NOT NULL,
  SBL_ID INT NOT NULL,
  SBS_DONE TIMESTAMP,
  PRIMARY KEY(SBS_ID),
  FOREIGN KEY(SB_ID) REFERENCES SCRUM_BOARDS(SB_ID),
  FOREIGN KEY(SBL_ID) REFERENCES SB_LANES(SBL_ID)
);

/

--for status, 0-incomplete, 1-complete
CREATE TABLE SB_TASKS(
  SBT_ID INT,
  SBS_ID INT NOT NULL,
  SBT_STATUS INT DEFAULT 0 NOT NULL,
  SBT_DESCRIPTION VARCHAR2(4000),
  PRIMARY KEY(SBT_ID),
  FOREIGN KEY(SBS_ID) REFERENCES SB_STORIES(SBS_ID)
);

/
--sequences for the tables that will need them
DROP SEQUENCE SU_SEQ;
DROP SEQUENCE SB_SEQ;
DROP SEQUENCE SBS_SEQ;
DROP SEQUENCE SBT_SEQ;

CREATE SEQUENCE SU_SEQ START WITH 1 INCREMENT BY 1;
CREATE SEQUENCE SB_SEQ START WITH 1 INCREMENT BY 1;
CREATE SEQUENCE SBS_SEQ START WITH 1 INCREMENT BY 1;
CREATE SEQUENCE SBT_SEQ START WITH 1 INCREMENT BY 1;
/

--triggers for the sequences

CREATE OR REPLACE TRIGGER SU_SEQ_TRIGGER
BEFORE INSERT ON SCRUM_USERS
FOR EACH ROW
BEGIN
  IF :NEW.USER_ID IS NULL THEN
    SELECT SU_SEQ.NEXTVAL INTO :NEW.USER_ID FROM DUAL;
  END IF;
END;
/

CREATE OR REPLACE TRIGGER SB_SEQ_TRIGGER
BEFORE INSERT ON SCRUM_BOARDS
FOR EACH ROW
BEGIN
  IF :NEW.SB_ID IS NULL THEN
    SELECT SB_SEQ.NEXTVAL INTO :NEW.SB_ID FROM DUAL;
  END IF;
END;
/

CREATE OR REPLACE TRIGGER SBS_SEQ_TRIGGER
BEFORE INSERT ON SB_STORIES
FOR EACH ROW
BEGIN
  IF :NEW.SBS_ID IS NULL THEN
    SELECT SBS_SEQ.NEXTVAL INTO :NEW.SBS_ID FROM DUAL;
  END IF;
END;
/
CREATE OR REPLACE TRIGGER SBT_SEQ_TRIGGER
BEFORE INSERT ON SB_TASKS
FOR EACH ROW
BEGIN
  IF :NEW.SBT_ID IS NULL THEN
    SELECT SBT_SEQ.NEXTVAL INTO :NEW.SBT_ID FROM DUAL;
  END IF;
END;
/
COMMIT;
/