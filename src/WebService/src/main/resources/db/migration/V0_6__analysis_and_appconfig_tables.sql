CREATE TABLE PERMISSION_ANALYSIS (
	ID				INT				NOT NULL  	AUTO_INCREMENT,
    PERMISSIONS		VARCHAR(256)	NOT NULL,
    SCORE			FLOAT			NOT NULL	DEFAULT 0,
    GROUP_ID		INT				NOT NULL	DEFAULT 1,
    PRIMARY KEY (ID)
);

CREATE TABLE APP_CONFIG (
	ID				INT				NOT NULL  	AUTO_INCREMENT,
    KEY_NAME		VARCHAR(256)	NOT NULL,
    CONFIG_VALUE	VARCHAR(512)	NOT NULL,
    DESCRIPTION		VARCHAR(512),
    PRIMARY KEY (ID)
);