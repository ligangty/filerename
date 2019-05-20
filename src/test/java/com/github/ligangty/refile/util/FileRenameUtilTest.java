package com.github.ligangty.refile.util;

import java.io.File;
import java.io.FileNotFoundException;

import com.github.ligangty.refile.handle.TemplateException;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class FileRenameUtilTest {

    private static final String filePath = System.getProperty("java.io.tmpdir") + "/renamefiletest";
    private static final String template = "<1-4>???";

    private File clearFilePath() {
        File fPath = new File(filePath);
        if (fPath.exists()) {
            fPath.delete();
        }
        return fPath;
    }

    @Before
    public void setUp() throws Exception {
        clearFilePath().mkdir();
    }

    @Test
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

    @After
    public void tearDown() throws Exception {
        clearFilePath();
    }
}
