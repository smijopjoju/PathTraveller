package com.playment.path.traveller.domain;

import java.util.List;
import java.util.StringJoiner;

public class Directory {

	private String dirName;
	
	private Directory parentDir;
	
	private List childDir;
	
	public Directory(String myName, Directory myParent) {
		this.dirName = myName;
		this.parentDir = myParent;
	}

	public String getDirName() {
		return dirName;
	}

	public Directory getParentDir() {
		return parentDir;
	}

	public List getChildDir() {
		return childDir;
	}

	public void setChildDir(List childDir) {
		this.childDir = childDir;
	}
	
	@Override
	public String toString() {		 
		StringBuilder myStr = new StringBuilder();
		myStr.append( parentDir != null && parentDir.toString() != null ? parentDir.toString() : "");		
		myStr.append(this.getDirName() != null ? "/" + this.getDirName() : "");
		return myStr.toString();
	}
}
