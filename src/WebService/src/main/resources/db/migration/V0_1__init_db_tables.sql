CREATE TABLE APP_CATEGORY (
	ID			INT				NOT NULL  AUTO_INCREMENT,
    TITLE 		VARCHAR(512)	NOT NULL,
    DESCRIPTION	VARCHAR(2048),
	PRIMARY KEY (ID)
);

CREATE TABLE APPLICATION (
	ID			INT				NOT NULL  AUTO_INCREMENT,
    NAME 		VARCHAR(1024)	NOT NULL,
    DESCRIPTION	VARCHAR(2048),
    VERSION		VARCHAR(64),
	PRIMARY KEY (ID)
);

CREATE TABLE PERMISSION (
	ID			INT				NOT NULL  AUTO_INCREMENT,
    TITLE 		VARCHAR(512)	NOT NULL,
    DESCRIPTION	VARCHAR(2048),
	PRIMARY KEY (ID)
);

CREATE TABLE THREAT (
	ID			INT				NOT NULL  AUTO_INCREMENT,
    NAME 		VARCHAR(1024)	NOT NULL,
    DESCRIPTION	VARCHAR(2048),
    LEVEL 		INT				NOT NULL,
	PRIMARY KEY (ID)
);

-- Relation tables

CREATE TABLE APPLICATION_APP_CATEGORY (
	ID			INT				NOT NULL  AUTO_INCREMENT,
    APP_ID 		INT				NOT NULL,
    APP_CAT_ID	INT				NOT NULL,
    
	PRIMARY KEY (ID),
    FOREIGN KEY (APP_ID) REFERENCES APPLICATION(ID),
    FOREIGN KEY (APP_CAT_ID) REFERENCES APP_CATEGORY(ID)
);

CREATE TABLE APPLICATION_PERMISSIONS (
	ID				INT		NOT NULL  AUTO_INCREMENT,
    APP_ID 			INT		NOT NULL,
    PERMISSION_ID	INT		NOT NULL,
    
	PRIMARY KEY (ID),
    FOREIGN KEY (APP_ID) REFERENCES APPLICATION(ID),
    FOREIGN KEY (PERMISSION_ID) REFERENCES PERMISSION(ID)
);

CREATE TABLE APPLICATION_THREAT (
	ID				INT		NOT NULL  AUTO_INCREMENT,
    APP_ID 			INT		NOT NULL,
    THREAT_ID		INT		NOT NULL,
    
	PRIMARY KEY (ID),
    FOREIGN KEY (APP_ID) REFERENCES APPLICATION(ID),
    FOREIGN KEY (THREAT_ID) REFERENCES THREAT(ID)
);