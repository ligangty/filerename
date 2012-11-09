package gang.li.refile.util;

import gang.li.refile.handle.FileNameObj;
import gang.li.refile.handle.NumTemplate;
import gang.li.refile.handle.OriginalFileNameTemplate;
import gang.li.refile.handle.RangeTemplate;
import gang.li.refile.handle.TemplateException;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Iterator;
import java.util.Vector;

/**
 *
 * @author runanli
 * @date April 12, 2009
 */
public class FileRenameUtil {

    private static final OriginalFileNameTemplate ORI_FILE_TEMPLATE = new OriginalFileNameTemplate();
    private static final RangeTemplate RANGE_TEMPLATE = new RangeTemplate();
    private static final NumTemplate NUM_TEMPLATE = new NumTemplate();

    public static void changeNameInOnePathFromTemplate(String filePath,
            String template) throws FileNotFoundException, TemplateException {
        Vector fileList = getFileNameObjsInOnePath(filePath);
        renameFilesUsingNum(fileList, template, 0, true);
    }

    public static Vector getFileNameObjsInOnePath(String filePath)
            throws RuntimeException {
        File file = new File(filePath);
        Vector fileList = new Vector();
        if (!file.isDirectory()) {
            throw new RuntimeException(filePath + "is not a directory!");
        }
        File[] files = file.listFiles();
        for (int i = 0; i < files.length; i++) {
            fileList.add(i, new FileNameObj(files[i].getAbsolutePath()));
        }
        return fileList;
    }

    /**
     *
     *
     * @param fileNames - a Vector contains the FileNameObjs
     * @param template - template String for renaming
     * @param isRenamable - true if you want to really rename the file, false if
     * you just want to get the renamed file name
     * @throws gang.li.refile.handle.TemplateException - when template is not
     * @throws java.io.FileNotFoundException
     */
    public static void renameFilesUsingNum(Vector fileNames, String template, int startIndex, boolean isRenamable) throws TemplateException, FileNotFoundException {
        FileNameObj fnObj = null;
        int fileIndex = startIndex;
        for (Iterator iter = fileNames.iterator(); iter.hasNext();) {
            fnObj = (FileNameObj) iter.next();
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
