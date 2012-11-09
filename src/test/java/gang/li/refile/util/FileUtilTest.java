package gang.li.refile.util;

import junit.framework.TestCase;

public class FileUtilTest extends TestCase {

    protected void setUp() throws Exception {
        super.setUp();
    }

    protected void tearDown() throws Exception {
        super.tearDown();
    }

    public void testGetPostFixFromFileName() {
        String fileName = "MyFile.txt";
        assertEquals("txt", FileUtil.getPostFixFromFileName(fileName));
        fileName = "MyFile";
        assertEquals("", FileUtil.getPostFixFromFileName(fileName));
        fileName = "MyFile.";
        assertEquals("", FileUtil.getPostFixFromFileName(fileName));
    }

    public void testGetFileNameWithoutPostFix() {
        String fileName = "MyFile.txt";
        assertEquals("MyFile", FileUtil.getFileNameWithoutPostFix(fileName));
        fileName = "MyFile";
        assertEquals("MyFile", FileUtil.getFileNameWithoutPostFix(fileName));
        fileName = "MyFile.";
        assertEquals("MyFile", FileUtil.getFileNameWithoutPostFix(fileName));
    }
}
