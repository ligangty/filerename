package gang.li.refile.handle;

import junit.framework.TestCase;

public class NumTemplateTest extends TestCase {

    private NumTemplate template = new NumTemplate();

    protected void setUp() throws Exception {
        super.setUp();
    }

    protected void tearDown() throws Exception {
        super.tearDown();
    }

    public void testGetFileNameFromTemplate() throws Exception {
        String templateStr = "Test????Temp?New?File";
        long startTime = System.currentTimeMillis();
        int loopTimes = 100000;
        try {
            for (int i = 0; i < loopTimes; i++) {
                String repFileName = template.getFileNameFromTemplate(templateStr, i);
                String expected = "Test" + i + "Temp" + i + "New" + i + "File.txt";
                if (i < 10) {
                    expected = "Test000" + i + "Temp" + i + "New" + i + "File.txt";

                } else if (i < 100) {
                    expected = "Test00" + i + "Temp" + i + "New" + i + "File.txt";
                } else if (i < 1000) {
                    expected = "Test0" + i + "Temp" + i + "New" + i + "File.txt";
                }
                assertEquals(expected, repFileName + ".txt");
            }
        } catch (TemplateException e) {
            fail(e.getMessage());
        }
        long endTime = System.currentTimeMillis();
        System.out.println(loopTimes + " times need time:" + (endTime - startTime) / 1000.0 + "s");
    }

    public void testAddZerosByNumLimit() {
        try {
            template.setChangeTo(5);
            assertEquals("0000000005", template.addZerosByNumLimit(10));
            template.setChangeTo(25);
            assertEquals("0000000025", template.addZerosByNumLimit(10));
        } catch (TemplateException e) {
            fail(e.getMessage());
        }
    }
}
