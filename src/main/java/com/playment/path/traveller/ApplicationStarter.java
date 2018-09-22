package com.playment.path.traveller;

import java.util.Scanner;

import com.playment.path.traveller.constants.AppConstants;
import com.playment.path.traveller.domain.CurrentDirectory;
import com.playment.path.traveller.domain.Directory;
import com.playment.path.traveller.handler.DirectoryTravelHandler;

public class ApplicationStarter {

	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);
		try {
				System.out.println("<Application Started...>");
				Directory rootDirectory = new Directory("ROOT", null);
				CurrentDirectory currentDirPointer = new CurrentDirectory();
				currentDirPointer.setCurrentDir(rootDirectory);
				DirectoryTravelHandler myHandler = new DirectoryTravelHandler(currentDirPointer);
				myHandler.support();
				while(true) {
					String userInput = in.nextLine();
					if( userInput != null ) {
						int seperatorIndex =  userInput.indexOf(" ");
						String command = null;
						String directory = null;
						if( seperatorIndex != -1) {
							command = userInput.substring(0, seperatorIndex);
							directory = userInput.substring(seperatorIndex+1,userInput.length());
						} else {
							command = userInput;
						}
						
						
						switch (command.toUpperCase()) {
							case "CD":
										System.out.println(myHandler.traversToDir(directory));
								break;
	
							case "PWD":
										System.out.println(myHandler.getMyCurrentDir());
								break;
							case "MKDIR":
										System.out.println(myHandler.createDirectory(directory));
								break;	
							case "LS":
										myHandler.listAllMyChilds();
								break;
							case "RM":
										System.out.println(myHandler.removeDirectory(directory));
								break;
							case "SESSION CLEAR":
										System.out.println(myHandler.clearSession());
								break;
							case "EXIT":
										myHandler.exit();
								break;
							case "SUPPORT":
									myHandler.support();
								break;	
							default:
								System.out.println(AppConstants.BAD_INPUT);
								break;
						}
					} else {
						System.out.println(AppConstants.BAD_INPUT);
					}
					
				}
		} catch(Exception e) {
			e.printStackTrace();
		} finally {
			in.close();
		}
		
	}

}
