package com.playment.path.traveller.domain;

import org.junit.Assert;
import org.junit.Test;



public class DirectoryTest {

	@Test
	public void testDirNull() {
		Directory parentDir = new Directory(null, null);
		Directory child = new Directory("dir1", parentDir);		
		Assert.assertEquals("/dir1", child.toString());
	}
	
	@Test
	public void testDirWithMultipleChild() {
		Directory parentDir = new Directory(null, null);
		Directory child1 = new Directory("dir1", parentDir);
		Directory child2 = new Directory("dir2", child1);
		Directory child = new Directory("dir3", child2);
		System.out.println(child.toString());
		Assert.assertEquals("/dir1/dir2/dir3", child.toString());
	}
}
