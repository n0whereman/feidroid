ALTER TABLE APPLICATION ADD FINGERPRINT VARCHAR(64);
ALTER TABLE APPLICATION_PERMISSIONS ADD IS_USED BIT(1) DEFAULT TRUE;
