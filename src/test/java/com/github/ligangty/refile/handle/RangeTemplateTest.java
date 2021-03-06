package com.github.ligangty.refile.handle;

import org.junit.Test;

import static org.junit.Assert.*;

public class RangeTemplateTest {
	private final RangeTemplate template = new RangeTemplate();

	@Test
	public void testGetFileNameFromTemplate() {
		String fileName = "MyFile.txt";
		String templateStr = "Test<1-2>Temp<1-2>New<3>i<5-6>";
		try {
			assertEquals("TestMyTempMyNewFile.txt", template.getFileNameFromTemplate(fileName, templateStr) + ".txt");
		} catch (TemplateException e) {
			fail();
		}
	}

	@Test
	public void testGetFileNameTemplate2() throws TemplateException{
		String fileName = "[ABC.XYZ][123456][这是一个测试啊]这是一个测试啊.txt";
		String templateStr = "Test<11-12>Temp<1-2>New<3>i<5-6>";
		assertEquals("Test12Temp[ANewBi.X.txt", template.getFileNameFromTemplate(fileName, templateStr) + ".txt");
		templateStr = "Test<11->";
		assertEquals("Test123456][这是一个测试啊]这是一个测试啊.txt", template.getFileNameFromTemplate(fileName, templateStr) + ".txt");
		templateStr = "Test<-11>";
		assertEquals("Test[ABC.XYZ][1.txt", template.getFileNameFromTemplate(fileName, templateStr) + ".txt");
	}

	@Test
	public void testGetRealTxtWithTemplateFromFileName() {
		String fileName = "MyFile";
		try {
			assertEquals("MyF", template.getRealTxtWithTemplateFromFileName(fileName, "<1-3>"));
			assertEquals("F", template.getRealTxtWithTemplateFromFileName(fileName, "<3>"));
		} catch (TemplateException e) {
			fail();
		}
	}

}
