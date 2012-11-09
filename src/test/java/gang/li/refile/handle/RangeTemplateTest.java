package gang.li.refile.handle;

import junit.framework.TestCase;

public class RangeTemplateTest extends TestCase {
	private RangeTemplate template = new RangeTemplate();
	
	protected void setUp() throws Exception {
		super.setUp();
	}

	protected void tearDown() throws Exception {
		super.tearDown();
	}

	public void testGetFileNameFromTemplate() {
		String fileName = "MyFile.txt";
		String templateStr = "Test<1-2>Temp<1-2>New<3>i<5-6>";
		try {
			assertEquals("TestMyTempMyNewFile.txt", template.getFileNameFromTemplate(
					fileName, templateStr)+".txt");
		} catch (TemplateException e) {
			fail();
		}
	}

	public void testGetRealTxtWithTemplateFromFileName() {
		String fileName = "MyFile";
		try {
			assertEquals("MyF",template.getRealTxtWithTemplateFromFileName(fileName, "<1-3>"));
			assertEquals("F",template.getRealTxtWithTemplateFromFileName(fileName, "<3>"));
		} catch (TemplateException e) {
			fail();
		}
	}

}
