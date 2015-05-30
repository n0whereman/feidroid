DELETE FROM APP_CONFIG;
INSERT INTO APP_CONFIG (KEY_NAME, CONFIG_VALUE, DESCRIPTION) VALUES 
	("PERMISSION_ANALYSIS_MODULE_ENABLED", "TRUE", "Switch to enable/disable permission analysis module"),
	("PERMISSION_ANALYSIS_MODULE_WEIGHT", "10", "Weight of permission analysis module"),
	("PERMISSION_ANALYSIS_MODULE_GROUPS", "3,5", "List of groups from permission analysis table to be included in analysis"),
	
	("SIMPLE_ANALYSIS_MODULE_ENABLED", "TRUE", "Switch to enable/disable simple analysis (Muska) module"),	
	("SIMPLE_ANALYSIS_MODULE_WEIGHT", "1", "Weight of simple analysis module"),
	
	("PERMISSION_USAGE_ANALYSIS_MODULE_ENABLED", "TRUE", "Switch to enable/disable permission usage analysis module"),	
	("PERMISSION_USAGE_ANALYSIS_MODULE_WEIGHT", "1", "Weight of permission usage analysis module"),
	
	("METHOD_ANALYSIS_MODULE_ENABLED", "TRUE", "Switch to enable/disable method analysis module"),	
	("METHOD_ANALYSIS_MODULE_WEIGHT", "1", "Weight of method analysis module");