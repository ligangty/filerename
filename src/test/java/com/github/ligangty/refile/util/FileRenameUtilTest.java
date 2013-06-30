package com.github.ligangty.refile.util;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Enumeration;
import java.util.Properties;

import com.github.ligangty.refile.handle.TemplateException;
import com.github.ligangty.refile.util.FileRenameUtil;

import junit.framework.TestCase;

public class FileRenameUtilTest extends TestCase {

    private static final String filePath = System.getProperty("java.io.tmpdir") + "/renamefiletest";
    private static final String template = "<1-4>???";

    private File clearFilePath() {
        File fPath = new File(filePath);
        if (fPath.exists()) {
            fPath.delete();
        }
        return fPath;
    }

    @Override
    protected void setUp() throws Exception {
        clearFilePath().mkdir();
    }

    public void testChangeNameInOnePathFromTemplate() {
        try {
            FileRenameUtil.changeNameInOnePathFromTemplate(filePath, template);
        } catch (FileNotFoundException e) {
            fail(e.getMessage());
        } catch (TemplateException e) {
            fail(e.getMessage());
        } catch (RuntimeException e) {
            fail(e.getClass() + ":" + e.getMessage());
        }

    }

    public void testGetFileNameObjsInOnePath() {
        try {
            FileRenameUtil.getFileNameObjsInOnePath(filePath);
        } catch (RuntimeException e) {
            fail(e.getClass() + ":" + e.getMessage());
        }
    }

    @Override
    protected void tearDown() throws Exception {
        clearFilePath();
    }
}
