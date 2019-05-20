package com.github.ligangty.refile.handle;

import org.junit.Test;

import static org.junit.Assert.*;

public class NumTemplateTest {

    private final NumTemplate template = new NumTemplate();

    @Test
    public void testGetFileNameFromTemplate() {
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
