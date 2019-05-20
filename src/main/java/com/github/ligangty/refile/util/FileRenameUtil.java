package com.github.ligangty.refile.util;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;

import com.github.ligangty.refile.handle.FileNameObj;
import com.github.ligangty.refile.handle.NumTemplate;
import com.github.ligangty.refile.handle.OriginalFileNameTemplate;
import com.github.ligangty.refile.handle.RangeTemplate;
import com.github.ligangty.refile.handle.TemplateException;

/**
 *
 * @author ligangty@github.com
 * @date April 12, 2009
 */
public class FileRenameUtil {

    private static final OriginalFileNameTemplate ORI_FILE_TEMPLATE = new OriginalFileNameTemplate();
    private static final RangeTemplate RANGE_TEMPLATE = new RangeTemplate();
    private static final NumTemplate NUM_TEMPLATE = new NumTemplate();

    public static void changeNameInOnePathFromTemplate(String filePath,
            String template) throws FileNotFoundException, TemplateException {
        List<FileNameObj> fileList = getFileNameObjsInOnePath(filePath);
        renameFilesUsingNum(fileList, template, 0, true);
    }

    public static List<FileNameObj> getFileNameObjsInOnePath(String filePath)
            throws RuntimeException {
        File file = new File(filePath);
        List<FileNameObj> fileList = new ArrayList<>();
        if (!file.isDirectory()) {
            throw new RuntimeException(filePath + "is not a directory!");
        }
        File[] files = file.listFiles();
        if(files!=null && files.length>0) {
            for (int i = 0; i < files.length; i++) {
                fileList.add(i, new FileNameObj(files[i].getAbsolutePath()));
            }
        }
        return fileList;
    }

    /**
     *
     *
     * @param fileNames - a Vector contains the FileNameObjs
     * @param template - template String for renaming
     * @param startIndex - 
     * @param isRenamable - true if you want to really rename the file, false if
     * you just want to get the renamed file name
     * @throws com.github.ligangty.refile.handle.TemplateException - when template is not
     * @throws java.io.FileNotFoundException -
     */
    public static void renameFilesUsingNum(List<FileNameObj> fileNames, String template, int startIndex, boolean isRenamable) throws TemplateException, FileNotFoundException {
        FileNameObj fnObj;
        int fileIndex = startIndex;
        for (FileNameObj fileName : fileNames) {
            fnObj = fileName;
            fnObj.setNewFileName(ORI_FILE_TEMPLATE.getFileNameFromTemplate(fnObj.getOldFileName(), template));
            fnObj.setNewFileName(RANGE_TEMPLATE.getFileNameFromTemplate(fnObj.getOldFileName(), fnObj.getNewFileName()));
            fnObj.setNewFileName(NUM_TEMPLATE.getFileNameFromTemplate(fnObj.getNewFileName(), fileIndex));
            if (isRenamable) {
                fnObj.renameFile();
            }
            fileIndex++;
        }
    }
}
