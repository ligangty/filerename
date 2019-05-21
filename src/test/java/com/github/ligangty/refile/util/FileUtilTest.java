package com.github.ligangty.refile.util;

import org.junit.Test;

import static org.junit.Assert.*;

public class FileUtilTest {

	@Test
	public void testGetPostFixFromFileName() {
		String fileName = "MyFile.txt";
		assertEquals("txt", FileUtil.getPostFixFromFileName(fileName));
		fileName = "MyFile";
		assertEquals("", FileUtil.getPostFixFromFileName(fileName));
		fileName = "MyFile.";
		assertEquals("", FileUtil.getPostFixFromFileName(fileName));
	}

	@Test
	public void testGetFileNameWithoutPostFix() {
		String fileName = "MyFile.txt";
		assertEquals("MyFile", FileUtil.getFileNameWithoutPostFix(fileName));
		fileName = "MyFile";
		assertEquals("MyFile", FileUtil.getFileNameWithoutPostFix(fileName));
		fileName = "MyFile.";
		assertEquals("MyFile", FileUtil.getFileNameWithoutPostFix(fileName));
		fileName = "[Maho][xyzabc][这是一个测试]这是一个测试.txt";
		assertEquals("[Maho][xyzabc][这是一个测试]这是一个测试", FileUtil.getFileNameWithoutPostFix(fileName));
	}

}
