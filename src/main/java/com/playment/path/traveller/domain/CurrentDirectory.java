package com.playment.path.traveller.domain;

public class CurrentDirectory {

	private Directory currentDir;

	public CurrentDirectory() {}
	
	public Directory getCurrentDir() {
		return currentDir;
	}

	public void setCurrentDir(Directory currentDir) {
		this.currentDir = currentDir;
	}
	
	@Override
	public String toString() {
		return currentDir != null ? currentDir.toString() : "";
	}
}
