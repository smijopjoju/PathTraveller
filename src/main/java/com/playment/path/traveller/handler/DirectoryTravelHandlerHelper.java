package com.playment.path.traveller.handler;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.playment.path.traveller.constants.AppConstants;
import com.playment.path.traveller.domain.Directory;

public class DirectoryTravelHandlerHelper {

	//Method to create child directories, it will make sure all the relations are update with both child and parent
	public String createMyChilds(String dir, Directory parentDir ) {
		
		String status = null;
		try {
				Directory myDir = null;
				List<Directory> childDirList = parentDir.getChildDir();
				//Validate that the 
				if( dir.contains("/") && dir.length() > 1 ) {
					int startIndex = dir.indexOf("/")+1;
					int endIndex = 0;
					String myDirName = null;
					if(startIndex != 1) {
						endIndex = startIndex; 
						myDirName = dir.substring(0,endIndex-1);
					} else {
						endIndex = (dir.indexOf("/", dir.indexOf("/")+1) > -1 ? dir.indexOf("/", dir.indexOf("/")+1) : dir.length()); 
						myDirName = dir.substring(startIndex,endIndex);
					}
					
					dir = endIndex != dir.length()  ?  dir.substring(endIndex,dir.length()) : null;
					
					if( childDirList != null ) {
						Map<String,Directory> resultMap = findMyChild(childDirList, myDirName);
						if( !resultMap.isEmpty() ) {
							myDir = resultMap.get(AppConstants.DIRECTORY_OBJECT);
							status = AppConstants.CREATE_PATH_EXIST;
						} else {
							myDir = new Directory(myDirName, parentDir);
							status = AppConstants.CREATE_SUCCESS;
							//Updating the parent relation
							if( parentDir.getChildDir() != null &&  !parentDir.getChildDir().isEmpty()) {
								parentDir.getChildDir().add(myDir);
							} else {
								List<Directory> myChildsList = new ArrayList();
								myChildsList.add(myDir);
								parentDir.setChildDir(myChildsList);
							}
						}
					} else { 
						myDir = new Directory(myDirName, parentDir);
						
						status = AppConstants.CREATE_SUCCESS;
						//Updating the parent relation
						if( parentDir.getChildDir() != null &&  !parentDir.getChildDir().isEmpty()) {
							parentDir.getChildDir().add(myDir);
						} else {
							List<Directory> myChildsList = new ArrayList();
							myChildsList.add(myDir);
							parentDir.setChildDir(myChildsList);
						}
					}
					
								
				} else if( !dir.contains("/") ){
					Map<String,Directory> resultMap = findMyChild(childDirList, dir);
					if( !resultMap.isEmpty() ) {
						myDir = resultMap.get(AppConstants.DIRECTORY_OBJECT);
						status = AppConstants.CREATE_PATH_EXIST;
					} else {
						myDir = new Directory(dir, parentDir);
						
						//Updating the parent relation
						if( parentDir.getChildDir() != null &&  !parentDir.getChildDir().isEmpty()) {
							parentDir.getChildDir().add(myDir);
						} else {
							List<Directory> myChildsList = new ArrayList();
							myChildsList.add(myDir);
							parentDir.setChildDir(myChildsList);
						}
						
						status = AppConstants.CREATE_SUCCESS;
					}
					dir = null;
				}
				
				
				
				
				//If the directory still have child's then make a recursive call
				if( dir != null && dir.length() > 0 && myDir != null) {
					status = createMyChilds(dir, myDir);
				}
		} catch(Exception e) {
			e.printStackTrace();
		}
		
		return status;
	}
	
	//Function to find if the specified child directory is existing or not, if exist the return that directory or will return an empty map
	public Map<String,Directory> findMyChild(List<Directory> childDirList, String childDirName) {
		
		Map<String,Directory> returnMap = new HashMap<>();
		if( childDirList != null && !childDirList.isEmpty()) {
			for( Directory childObj : childDirList) {
				if(childObj != null && childDirName !=null && childDirName.equalsIgnoreCase(childObj.getDirName())) {								
					returnMap.put(AppConstants.DIRECTORY_OBJECT, childObj);
					break;
				}
			}
		}
		
		return returnMap;
	}
	
	//Method to remove child directories
	public String removeMyChilds(String dir, Directory parentDir ) {
		
		String status = null;
		try {
				Directory myDir = null;
				List<Directory> childDirList = parentDir != null ? parentDir.getChildDir() : null;
				//Validate that the 
				if( dir.contains("/") && dir.length() > 1 ) {
					int startIndex = dir.indexOf("/")+1;
					int endIndex = 0;
					String myDirName = null;
					if(startIndex != 1) {
						endIndex = startIndex; 
						myDirName = dir.substring(0,endIndex-1);
					} else {
						endIndex = (dir.indexOf("/", dir.indexOf("/")+1) > -1 ? dir.indexOf("/", dir.indexOf("/")+1) : dir.length()); 
						myDirName = dir.substring(startIndex,endIndex);
					}
					
					dir = endIndex != dir.length()  ?  dir.substring(endIndex,dir.length()) : null;
					if( childDirList != null ) {
						Map<String,Directory> resultMap = findMyChild(childDirList, myDirName);
						if( !resultMap.isEmpty() ) {
							
							myDir = resultMap.get(AppConstants.DIRECTORY_OBJECT);
							if(dir == null) {
								parentDir.getChildDir().remove(myDir);
								status = AppConstants.REMOVE_SUCCESS;
							}
							
						} else {
							status = AppConstants.REMOVE_ERROR_PATH_INVALIDE;
						}
					} else { 
						if(dir == null) {
							Directory superParent = parentDir != null ? parentDir.getParentDir() : null;
							if( superParent != null ) {
								superParent.getChildDir().remove(parentDir);
							} else {
								parentDir = null;
							}
							
							status = AppConstants.REMOVE_SUCCESS;
						}
					}
					
								
				} else if( !dir.contains("/") ){
					Map<String,Directory> resultMap = findMyChild(childDirList, dir);
					if( !resultMap.isEmpty() ) {
						myDir = resultMap.get(AppConstants.DIRECTORY_OBJECT);
						parentDir.getChildDir().remove(myDir);
						status = AppConstants.REMOVE_SUCCESS;
					} else {
						Directory superParent = parentDir.getParentDir();
						superParent.getChildDir().remove(parentDir);
						status = AppConstants.REMOVE_SUCCESS;
					}
					dir = null;
				}
				
				//If the directory still have child's then make a recursive call
				if( dir != null && dir.length() > 0 && myDir != null) {
					status = removeMyChilds(dir, myDir);
				}
		} catch(Exception e) {
			e.printStackTrace();
		}
		
		return status;
	}
	
	public Directory getMyRootDir(Directory dirObj) {
		Directory rootDir = null;
		try {
				if(dirObj.getParentDir() != null ) {
					rootDir = getMyRootDir(dirObj.getParentDir());
				} else {
					rootDir = dirObj;
				}
		} catch(Exception e) {
			e.printStackTrace();
		}
		return rootDir;
	}
	
	//Method to find specified directories
	public Directory findSpecifiedDir(String dir, Directory parentDir ) {
			
			Directory myDir = null;
			try {
					
					List<Directory> childDirList = parentDir != null ? parentDir.getChildDir() : null;
					//Validate that the 
					if( dir.contains("/") && dir.length() > 1 ) {
						int startIndex = dir.indexOf("/")+1;
						int endIndex = (dir.indexOf("/", dir.indexOf("/")+1) > -1 ? dir.indexOf("/", dir.indexOf("/")+1) : dir.length()); 
						String myDirName = dir.substring(startIndex,endIndex);
						
						
						String startingStr = null;
						if(startIndex != 1) {
							startingStr = dir.substring(0,startIndex-1);
						}
						
						dir = endIndex != dir.length() ?  dir.substring(endIndex,dir.length()) : null;
						if( startingStr != null && startingStr.equalsIgnoreCase("..")) {
							if(parentDir != null && parentDir.getParentDir() != null) {
								parentDir = parentDir.getParentDir();
								myDir = parentDir.getParentDir();
							} else {
								myDir = null;
							}
						} else {
							Map<String,Directory> resultMap = findMyChild(childDirList, startingStr);
							if( !resultMap.isEmpty() ) {
								
								myDir = resultMap.get(AppConstants.DIRECTORY_OBJECT);
								
							} else {
								myDir = null;
							}
						}
						
						if(myDirName != null && !myDirName.equalsIgnoreCase("..")) {
							if( childDirList != null ) {
								Map<String,Directory> resultMap = findMyChild(childDirList, myDirName);
								if( !resultMap.isEmpty() ) {
									
									myDir = resultMap.get(AppConstants.DIRECTORY_OBJECT);
									
								} else {
									myDir = null;
								}
							} 
						} else {
							
								if(parentDir != null && parentDir.getParentDir() != null) {
									myDir = parentDir.getParentDir();
								} else {
									myDir = null;
								}
							
						}
									
					} else if( !dir.contains("/") ){
						
						if( dir != null && dir.equalsIgnoreCase("..")) {
							if(parentDir != null && parentDir.getParentDir() != null) {
								myDir = parentDir.getParentDir();
							} else {
								myDir = null;
							}
						} else {
							Map<String,Directory> resultMap = findMyChild(childDirList, dir);
							if( !resultMap.isEmpty() ) {
								
								myDir = resultMap.get(AppConstants.DIRECTORY_OBJECT);
								
							} else {
								myDir = null;
							}
						}
						
						dir = null;
					}
					
					//If the directory still have child's then make a recursive call
					if( dir != null && dir.length() > 0 && !dir.equalsIgnoreCase("/") && myDir != null) {
						myDir = findSpecifiedDir(dir, myDir);
					}
			} catch(Exception e) {
				e.printStackTrace();
			}
			
			return myDir;
		}
}
