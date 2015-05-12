DELETE FROM APP_CONFIG;
INSERT INTO APP_CONFIG (KEY_NAME, CONFIG_VALUE, DESCRIPTION) VALUES 
	("PERMISSION_ANALYSIS_MODULE_ENABLED", "TRUE", "Switch to enable/disable permission analysis module"),
	("PERMISSION_ANALYSIS_MODULE_WEIGHT", "2", "Weight of permission analysis module"),
	
	("SIMPLE_ANALYSIS_MODULE_ENABLED", "TRUE", "Switch to enable/disable simple analysis (Muska) module"),	
	("SIMPLE_ANALYSIS_MODULE_WEIGHT", "1", "Weight of simple analysis module"),
	
	("PERMISSION_USAGE_ANALYSIS_MODULE_ENABLED", "TRUE", "Switch to enable/disable permission usage analysis module"),	
	("PERMISSION_USAGE_ANALYSIS_MODULE_WEIGHT", "1", "Weight of permission usage analysis module");