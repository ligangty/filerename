package com.github.ligangty.refile.handle;

import com.github.ligangty.refile.handle.OriginalFileNameTemplate;

import junit.framework.TestCase;

/**
 *
 * @author ligangty
 */
public class OriginalFileNameTemplateTest extends TestCase {

    private OriginalFileNameTemplate template = new OriginalFileNameTemplate();

    public void testGetFileNameFromTemplate() throws Exception {
        String fileName = "fileNameForTest.txt";
        String templateStr = "*<1-5>*??";
        String result = template.getFileNameFromTemplate(fileName, templateStr);
        assertEquals("fileNameForTest<1-5>fileNameForTest??", result);
    }
}
