package com.github.ligangty.refile.util;

/**
 * This util class is used to do some utility function to get file associated
 * properties, like get postfix, remove postfix and so on.
 *
 * @author ligangty@github.com
 * @date Apr 6, 2009
 */
public class FileUtil {

    /**
     * get the postfix of a file name(not including file path)
     *
     * @param fileName - file name not including the file path
     * @return the postfix of a file name
     */
    public static String getPostFixFromFileName(String fileName) {
        int lastIndexOfDot = fileName.lastIndexOf(".");
        if (lastIndexOfDot < 0) {
            return "";
        } else {
            return fileName.substring(lastIndexOfDot + 1);
        }
    }

    /**
     * remove the postfix of a filename(not including file path)
     *
     * @param fileName - file name not including the file path
     * @return the filename without its postfix
     */
    public static String getFileNameWithoutPostFix(String fileName) {
        int lastIndexOfDot = fileName.lastIndexOf(".");
        if (lastIndexOfDot < 0) {
            return fileName;
        } else {
            return fileName.substring(0, lastIndexOfDot);
        }
    }
}
