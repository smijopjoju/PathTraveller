package com.playment.path.traveller.handler;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import com.playment.path.traveller.constants.AppConstants;
import com.playment.path.traveller.domain.CurrentDirectory;
import com.playment.path.traveller.domain.Directory;



public class DirectoryTravelHandlerTest {
	
	DirectoryTravelHandler handler;
	CurrentDirectory currDir = new CurrentDirectory();
	@Before
	public void init() {
		Directory rootDir = new Directory("ROOT", null);
		currDir.setCurrentDir(rootDir);
		handler = new DirectoryTravelHandler(currDir);
	}
	
	@Test
	public void TestCreateDirectoryMethod() {
		String status1 = handler.createDirectory(null);
		Assert.assertTrue(status1.equalsIgnoreCase(AppConstants.CREATE_PATH_EMPTY));
		
		String status2 = handler.createDirectory("/");
		Assert.assertTrue(status2.equalsIgnoreCase(AppConstants.CREATE_PATH_INVALIDE));
		
		String status33 = handler.createDirectory("dir1");
		Assert.assertTrue(status33.equalsIgnoreCase(AppConstants.CREATE_SUCCESS));
		
		
		String status3 = handler.createDirectory("/dir1");
		Assert.assertTrue(status3.equalsIgnoreCase(AppConstants.CREATE_PATH_EXIST));
		
		String status4 = handler.createDirectory("/dir1");
		Assert.assertTrue(status4.equalsIgnoreCase(AppConstants.CREATE_PATH_EXIST));
		
		String status5 = handler.createDirectory("/dir1/dir2");
		Assert.assertTrue(status5.equalsIgnoreCase(AppConstants.CREATE_SUCCESS));
		
		String status6 = handler.createDirectory("/dir1/dir2");
		Assert.assertTrue(status6.equalsIgnoreCase(AppConstants.CREATE_PATH_EXIST));
		
		String status7 = handler.createDirectory("/dir1");
		Assert.assertTrue(status7.equalsIgnoreCase(AppConstants.CREATE_PATH_EXIST));
		

		String status8 = handler.createDirectory("/dir1/dir2/dir3");
		Assert.assertTrue(status8.equalsIgnoreCase(AppConstants.CREATE_SUCCESS));
	}
	
	@Test
	public void TestRemoveDirectoryMethod() {
		String status1 = handler.removeDirectory(null);
		Assert.assertTrue(status1.equalsIgnoreCase(AppConstants.REMOVE_PATH_EMPTY));
		
		String createStatus2 = handler.createDirectory("/");
		String status2 = handler.removeDirectory("/");
		Assert.assertTrue(status2.equalsIgnoreCase(AppConstants.REMOVE_ERROR_PATH_INVALIDE));
		
		String createStatus3 = handler.createDirectory("/dir1");
		String status3 = handler.removeDirectory("/dir1");
		Assert.assertTrue(status3.equalsIgnoreCase(AppConstants.REMOVE_SUCCESS));
		
		String status4 = handler.removeDirectory("/dir1");
		Assert.assertTrue(status4.equalsIgnoreCase(AppConstants.REMOVE_ERROR_PATH_INVALIDE));
		
		String createStatus5 = handler.createDirectory("/dir1/dir2");
		String status5 = handler.removeDirectory("/dir1");
		String status6 = handler.removeDirectory("/dir1/dir2");
		Assert.assertTrue(status5.equalsIgnoreCase(AppConstants.REMOVE_SUCCESS));
		Assert.assertTrue(status6.equalsIgnoreCase(AppConstants.REMOVE_ERROR_PATH_INVALIDE));
		
		
	}
	
	@Test
	public void TestTraversToDirMethod() {
		String status1 = handler.createDirectory("/dir1/dir2/dir3");
		Assert.assertTrue(status1.equalsIgnoreCase(AppConstants.CREATE_SUCCESS));
		
		String status2 = handler.traversToDir("/dir1/dir2");
		Assert.assertTrue(status2.equalsIgnoreCase(AppConstants.CHANGE_DIR_SUCCESS));
		String currentLocation = handler.getMyCurrentDir();
		Assert.assertTrue("/ROOT/dir1/dir2".equalsIgnoreCase(currentLocation));
		
		String status3 = handler.traversToDir("../../");
		Assert.assertTrue(status3.equalsIgnoreCase(AppConstants.CHANGE_DIR_SUCCESS));
		String currentLocation1 = handler.getMyCurrentDir();
		Assert.assertTrue("/ROOT".equalsIgnoreCase(currentLocation1));
		
		String status4 = handler.traversToDir("../../");
		Assert.assertTrue(status4.equalsIgnoreCase(AppConstants.CHANGE_DIR_ERROR));
		String currentLocation2 = handler.getMyCurrentDir();
		Assert.assertTrue("/ROOT".equalsIgnoreCase(currentLocation2));
		
		
		String status5 = handler.createDirectory("/dir1/dir2/dir3/dir4/dir5");
		Assert.assertTrue(status1.equalsIgnoreCase(AppConstants.CREATE_SUCCESS));
		String status6 = handler.traversToDir("/dir1/dir2/dir3/dir4/dir5");
		Assert.assertTrue(status6.equalsIgnoreCase(AppConstants.CHANGE_DIR_SUCCESS));
		String status7 = handler.traversToDir("../../..");
		Assert.assertTrue(status7.equalsIgnoreCase(AppConstants.CHANGE_DIR_SUCCESS));
		String currentLocation3 = handler.getMyCurrentDir();
		Assert.assertTrue("/ROOT/dir1/dir2".equalsIgnoreCase(currentLocation3));
		
	}
	
	@Test
	public void TestGetMyCurrentDirMethod() {
		String currentLocation3 = handler.getMyCurrentDir();
		Assert.assertTrue("/ROOT".equalsIgnoreCase(currentLocation3));
	}
	
	@Test
	public void TestClearSessionMethod() {
		String status1 = handler.clearSession();
		Assert.assertTrue(status1.equalsIgnoreCase(AppConstants.SESSION_CLEAR_SUCCESS));
		String currentLocation2 = handler.getMyCurrentDir();
		Assert.assertTrue("/ROOT".equalsIgnoreCase(currentLocation2));
	}
	
	@Test
	public void TestSupportMethod() {
		handler.support();
	}
}
