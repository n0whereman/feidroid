CREATE TABLE PERMISSION_USAGE_ANALYSIS (
	ID				INT				NOT NULL  	AUTO_INCREMENT,
    ORIGINATOR		INT				NOT NULL,
    RELATED_TO		INT				NOT NULL,
    PRIMARY KEY (ID)
);