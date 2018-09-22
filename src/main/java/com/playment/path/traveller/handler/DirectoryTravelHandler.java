package com.playment.path.traveller.handler;

import java.util.List;
import java.util.Map;

import com.playment.path.traveller.constants.AppConstants;
import com.playment.path.traveller.domain.CurrentDirectory;
import com.playment.path.traveller.domain.Directory;

public class DirectoryTravelHandler {
	
	private CurrentDirectory currentDir;
	
	public DirectoryTravelHandler(CurrentDirectory currentDir) {
		this.currentDir = currentDir;
	}
	
	//Method to create new directory
	public String createDirectory(String directory) {
		String status = null;
		try {
				if( directory != null) {
					
					// Getting current directory to add newly creating directories as its child
					Directory myDir = currentDir.getCurrentDir();
					DirectoryTravelHandlerHelper myHelper = new DirectoryTravelHandlerHelper();
					
					//Validate whether the directory contain more than 
					if( directory.contains(" ") && directory.length() != 1) {
						String dirList[] = directory.split(" ");
						
						
						for(String childDirName : dirList) {
							status = myHelper.createMyChilds(childDirName, myDir);
						}
						
					} else if ( directory.length() != 1 ) {
						status = myHelper.createMyChilds(directory, myDir);
					} else {
						status = AppConstants.CREATE_PATH_INVALIDE;
					}
				} else {
					status = AppConstants.CREATE_PATH_EMPTY;
				}
		} catch(Exception e) {
			e.printStackTrace();
		}
		return status;
	}
	
	//Method to remove specified directory
	public String removeDirectory( String directory) {
		String status = null;
		try {
			if( directory != null) {
				
				// Getting current directory to add newly creating directories as its child
				Directory myDir = currentDir.getCurrentDir();
				DirectoryTravelHandlerHelper myHelper = new DirectoryTravelHandlerHelper();
				
				//Validate whether the directory contain more than 
				if( directory.contains(" ") && directory.length() != 1) {
					String dirList[] = directory.split(" ");
					
					
					for(String childDirName : dirList) {
						status = myHelper.removeMyChilds(childDirName, myDir);
					}
					
				} else if ( directory.length() != 1 ) {
					status = myHelper.removeMyChilds(directory, myDir);
				} else {
					status = AppConstants.REMOVE_ERROR_PATH_INVALIDE;
				}
			} else {
				status = AppConstants.REMOVE_PATH_EMPTY;
			}
		} catch( Exception e) {
			e.printStackTrace();
		}
		return status;
	}
	
	//Method to travers to a specified directory
	public String traversToDir( String directory ) {
		String status = null;
		try {
				if( directory != null) {
					// Getting current directory to add newly creating directories as its child
					Directory myDir = currentDir.getCurrentDir();
					DirectoryTravelHandlerHelper myHelper = new DirectoryTravelHandlerHelper();
					
					if( myDir != null ) {
						myDir = myHelper.findSpecifiedDir(directory, myDir);
						if(myDir != null ) {
							currentDir.setCurrentDir(myDir);
							status = AppConstants.CHANGE_DIR_SUCCESS;
						} else {
							status = AppConstants.CHANGE_DIR_ERROR;
						}
					} else {
						status = AppConstants.CHANGE_DIR_ERROR;
					}
					
				}
				
		} catch( Exception e) {
			e.printStackTrace();
		}
		return status;
	}
	
	//Method to print current directory
	public String getMyCurrentDir() {
		String result = null;
		try {
				Directory currentDirObj = currentDir.getCurrentDir();
				result = currentDirObj.toString();
		} catch( Exception e) {
			e.printStackTrace();
		}
		return result;
	}
	
	//Method to clear the user activities
	public String clearSession() {
		String result = null;
		try {
				DirectoryTravelHandlerHelper myHelper = new DirectoryTravelHandlerHelper();
				Directory currentDirObj = currentDir.getCurrentDir();
				Directory rootDir = myHelper.getMyRootDir(currentDirObj);
				currentDir.setCurrentDir(rootDir);
				result = AppConstants.SESSION_CLEAR_SUCCESS;
		} catch( Exception e) {
			e.printStackTrace();
		}
		return result;
	}
	
	public void support() {
		try {
				String supportInfo[] = {
											" Command: mkdir {dir1} {dir2}	 	Description: Create new directory",
											" Command: cd {dir1} 				Description: To change current directory to specified directory",
											" Command: ls	 					Description: List all the child directories",
											" Command: pwd	 					Description: print the current directory",
											" Command: rm {dir1} 				Description: Remove the specified directory",
											" Command: session clear 			Description: Clear the current session, will clear all the user activities till now",
											" Command: support	 				Description: List all the supporting commands",
											" Command: exit 					Description: Exit from the application"
										};
				
				for (String s : supportInfo) {
				      
						if(System.console() != null) {
							System.console().writer().println(s);
						} else {
							System.out.println(s);
						}
				      
				    }
		} catch( Exception e ) {
			e.printStackTrace();
		}
	}
	
	public void exit() {
		System.exit(0);
	}
	
	public void listAllMyChilds() {
		try {
			Directory currentDirObj = currentDir.getCurrentDir();
			List<Directory> myChilds = currentDirObj.getChildDir();
			if(myChilds != null && !myChilds.isEmpty()) {
				for(Directory dirObj : myChilds) {
					System.out.println(dirObj.getDirName());
				}
			} else {
				System.out.println(AppConstants.CHILD_LIST_EMPTY);
			}
		} catch(Exception e) {
			e.printStackTrace();
		}
		
	}
}
 