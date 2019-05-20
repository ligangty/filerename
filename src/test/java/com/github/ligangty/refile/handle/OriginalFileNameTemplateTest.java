package com.github.ligangty.refile.handle;

import com.github.ligangty.refile.handle.OriginalFileNameTemplate;

import junit.framework.TestCase;
import org.junit.Assert;
import org.junit.Test;

public class OriginalFileNameTemplateTest {

    private final OriginalFileNameTemplate template = new OriginalFileNameTemplate();

    @Test
    public void testGetFileNameFromTemplate() throws Exception {
        String fileName = "fileNameForTest.txt";
        String templateStr = "*<1-5>*??";
        String result = template.getFileNameFromTemplate(fileName, templateStr);
        Assert.assertEquals("fileNameForTest<1-5>fileNameForTest??", result);
    }
}
