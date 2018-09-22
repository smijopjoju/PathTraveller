package com.playment.path.traveller.constants;

public class AppConstants {

	private AppConstants() {}
	
	public static final String DIRECTORY_OBJECT = "directoryObject";
	
	public static final String CREATE_SUCCESS = "SUCC: CREATED";
	
	public static final String CREATE_PATH_EXIST = "ERR: PATH ALREADY EXIST";
	
	public static final String CREATE_PATH_INVALIDE = "ERR : INVALIDE DIRECTORY";
	
	public static final String CREATE_PATH_EMPTY = "ERR : SPECIFIED DIRECTORY IS EMPTY";
	
	public static final String REMOVE_SUCCESS = "SUCC: DELETED";
	
	public static final String REMOVE_ERROR_PATH_INVALIDE = "ERR: INVALID PATH";
	
	public static final String REMOVE_PATH_EMPTY = "ERR : SPECIFIED DIRECTORY IS EMPTY";
	
	public static final String CHANGE_DIR_SUCCESS = "SUCC: REACHED";
	
	public static final String CHANGE_DIR_ERROR = "ERR: INVALID PATH";
	
	public static final String CHANGE_DIR_ROOT_REACHED = "ERR: ROOT REACHED";
	
	public static final String SESSION_CLEAR_SUCCESS = "SUCC: CLEARED: REST TO ROOT";
	
	public static final String BAD_INPUT = "Unrecognizable command";
	
	public static final String CHILD_LIST_EMPTY = "Nothing to show";
}
